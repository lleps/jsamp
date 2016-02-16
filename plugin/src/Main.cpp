/*
* JSAMP - Java implementation for SA:MP.
* Project started 13/4/15.
* Writted by spell <leandro.barbero122@gmail.com>
*/

#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <stdio.h>
#include <jni.h>
#include <cstring>

#include "sampgdk/sampgdk.h"
#include "EncodingUtils.h"
#include "FileUtils.h"
#include "JNISampFunctions.h"

// soltera - soltinha
using namespace std;

jclass function_class;
jclass callback_class;
JavaVM* jvm;

jmethodID getMethodAndReportIfNotFound(JNIEnv* env, char *name, char *signature);
vector<string>* getJarFilesInDirectory(const char *directory);
void checkForExceptions(JNIEnv* env);

// File used to get JVM options.
const char* JVM_FILE_PATH = "./jsamp/jvm-options";
// From which folder jar files will be loaded on JVM.
const char* JARS_PATH = "./jsamp/jars/";
// Callback listener class should contain a static method for every callback.
// This constant point to the class path.
const char* CALLBACK_LISTENER_CLASS = "com/lleps/jsamp/MainCallbackListener";
// Which class will be used to register all native methods into JVM.
const char* FUNCTIONS_CLASS = "com/lleps/jsamp/SAMPFunctions";

PLUGIN_EXPORT bool PLUGIN_CALL Load(void **ppData) {
    sampgdk::Load(ppData);
	sampgdk::logprintf("");
	sampgdk::logprintf("------------------------------------");
	sampgdk::logprintf("  JSAMP - Ultima compilacion: %s - %s", __DATE__, __TIME__);
	sampgdk::logprintf("------------------------------------");
	sampgdk::logprintf("");

	vector<string>* jarsForLoad = getJarFilesInDirectory(JARS_PATH);
	if (jarsForLoad == NULL) {
		sampgdk::logprintf("Error: No se encontro la carpeta '%s'.", JARS_PATH);
		return false;
	}

	string classPathOption = "-Djava.class.path=";
	for (int i = 0; i < jarsForLoad->size(); i++) {
		classPathOption += JARS_PATH + jarsForLoad->at(i);
		#ifdef __linux__
		classPathOption += ":";
		#else
		classPathOption += ";";
		#endif
		sampgdk::logprintf("Incluyendo: %s", jarsForLoad->at(i).c_str());
	}
	sampgdk::logprintf("%d jar cargado(s).\n", jarsForLoad->size());

	int vm_arg_count = 1;
	vector<string>* vm_options_file = loadFileLines(JVM_FILE_PATH);

	if (vm_options_file != NULL) {
		sampgdk::logprintf("Se incluirán opciones de jvm desde %s..", JVM_FILE_PATH);
		vm_arg_count += vm_options_file->size();
	}

	JavaVMOption *JVMoptions = new JavaVMOption[vm_arg_count];
	JVMoptions[0].optionString = (char*)classPathOption.c_str();
	sampgdk::logprintf(JVMoptions[0].optionString);

	if (vm_options_file != NULL) {
		sampgdk::logprintf("Incluyendo %d opciones desde %s..", vm_options_file->size(), JVM_FILE_PATH);
		for (int i = 0; i < vm_options_file->size(); i++) {
			if (!vm_options_file->at(i).empty()) {
				JVMoptions[i + 1].optionString = (char*)vm_options_file->at(i).c_str();
				sampgdk::logprintf("%d: %s", i + 1, JVMoptions[i + 1].optionString);
			}
		}
	}

	JavaVMInitArgs vm_args;
	vm_args.options = JVMoptions;
	vm_args.nOptions = vm_arg_count;
	vm_args.version = JNI_VERSION_1_8;
	vm_args.ignoreUnrecognized = JNI_FALSE;

	sampgdk::logprintf("\n  Iniciando maquina virtual.. ");
	JNIEnv *env;
	int create_jvm_result = JNI_CreateJavaVM(&jvm, (void**)&env, &vm_args);
	if (create_jvm_result < 0) {
		sampgdk::logprintf("Error %d creando la JVM.", create_jvm_result);
		return false;
	}

	const char *callback_class_name = CALLBACK_LISTENER_CLASS;
	callback_class = env->FindClass(callback_class_name);
	if (callback_class == NULL) {
		sampgdk::logprintf("Error: No se encontró la clase de llamadas '%s'", callback_class_name);
		return false;
	}

	const char *function_class_name = FUNCTIONS_CLASS;
	function_class = env->FindClass(function_class_name);
	if (function_class == NULL) {
		sampgdk::logprintf("Error: No se encontró la clase de funciones '%s'", function_class_name);
		return false;
	}

	int error_registering_methods = JNISampFunctions::RegisterNativeMethods(env, function_class);
	if (error_registering_methods) {
		sampgdk::logprintf("Error %d registrando métodos nativos", error_registering_methods);
		return false;
	}

	sampgdk::logprintf("Perfecto. Se inició el plugin correctamente!");
	return true;
}

PLUGIN_EXPORT void PLUGIN_CALL Unload() {
	sampgdk::Unload();
}

PLUGIN_EXPORT unsigned int PLUGIN_CALL Supports() {
	return sampgdk::Supports() | SUPPORTS_PROCESS_TICK;
}

PLUGIN_EXPORT void PLUGIN_CALL ProcessTick() {
	sampgdk::ProcessTick();
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "ProcessTick", "()V");
	if (method) {
		env->CallStaticVoidMethod(callback_class, method);
		checkForExceptions(env);
	}
}

PLUGIN_EXPORT bool PLUGIN_CALL OnGameModeInit() {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnGameModeInit", "()V");
	if (method) {
		env->CallStaticVoidMethod(callback_class, method, NULL);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnGameModeExit() {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnGameModeExit", "()V");
	if (method) {
		env->CallStaticVoidMethod(callback_class, method);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerRequestClass(int playerid, int classid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerRequestClass", "(II)I");
	int r = 1;
	if (method) {
		r = env->CallStaticIntMethod(callback_class, method, playerid, classid);
		checkForExceptions(env);
	}
	return r != 0;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerConnect(int playerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerConnect", "(I)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerDisconnect(int playerid, int reason) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerDisconnect", "(II)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, reason);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerSpawn(int playerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerSpawn", "(I)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerDeath(int playerid, int killerid, int reason) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerDeath", "(III)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, killerid, reason);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnVehicleSpawn(int vehicleid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnVehicleSpawn", "(I)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, vehicleid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnVehicleDeath(int vehicleid, int killerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnVehicleDeath", "(II)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, vehicleid, killerid);
		checkForExceptions(env);
	}
	return true;
}


PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerExitVehicle(int playerid, int vehicleid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerExitVehicle", "(II)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, vehicleid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerStateChange(int playerid, int newstate, int oldstate) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerStateChange", "(III)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, newstate, oldstate);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerEnterCheckpoint(int playerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerEnterCheckpoint", "(I)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerLeaveCheckpoint(int playerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerLeaveCheckpoint", "(I)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerEnterRaceCheckpoint(int playerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerEnterRaceCheckpoint", "(I)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerLeaveRaceCheckpoint(int playerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerLeaveRaceCheckpoint", "(I)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerRequestSpawn(int playerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerRequestSpawn", "(I)I");
	int r = 1;
	if (method) {
		r = env->CallStaticIntMethod(callback_class, method, playerid);
		checkForExceptions(env);
	}
	return r != 0;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnObjectMoved(int objectid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnObjectMoved", "(I)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, objectid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerObjectMoved(int playerid, int objectid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerObjectMoved", "(II)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, objectid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerPickUpPickup(int playerid, int pickupid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerPickUpPickup", "(II)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, pickupid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnVehicleMod(int playerid, int vehicleid, int componentid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnVehicleMod", "(III)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, vehicleid, componentid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnVehiclePaintjob(int playerid, int vehicleid, int paintjobid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnVehiclePaintjob", "(III)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, vehicleid, paintjobid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnVehicleRespray(int playerid, int vehicleid, int color1, int color2) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnVehicleRespray", "(IIII)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, vehicleid, color1, color2);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerSelectedMenuRow(int playerid, int row) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerSelectedMenuRow", "(II)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, row);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerExitedMenu(int playerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerExitedMenu", "(I)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerInteriorChange(int playerid, int newinteriorid, int oldinteriorid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerInteriorChange", "(III)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, newinteriorid, oldinteriorid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerKeyStateChange(int playerid, int newkeys, int oldkeys) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerKeyStateChange", "(III)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, newkeys, oldkeys);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerUpdate(int playerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerUpdate", "(I)I");
	int r = 1;
	if (method) {
		r = env->CallStaticIntMethod(callback_class, method, playerid);
		checkForExceptions(env);
	}
	return r != 0;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerStreamIn(int playerid, int forplayerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerStreamIn", "(II)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, forplayerid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerStreamOut(int playerid, int forplayerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerStreamOut", "(II)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, forplayerid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnVehicleStreamIn(int vehicleid, int forplayerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnVehicleStreamIn", "(II)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, vehicleid, forplayerid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnVehicleStreamOut(int vehicleid, int forplayerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnVehicleStreamOut", "(II)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, vehicleid, forplayerid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerClickPlayer(int playerid, int clickedplayerid, int source) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerClickPlayer", "(III)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, clickedplayerid, source);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnVehicleDamageStatusUpdate(int vehicleid, int playerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnVehicleDamageStatusUpdate", "(II)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, vehicleid, playerid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnEnterExitModShop(int playerid, int enterexit, int interiorid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnEnterExitModShop", "(III)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, enterexit, interiorid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnUnoccupiedVehicleUpdate(int vehicleid, int playerid, int passenger_seat, float new_x, float new_y, float new_z, float vel_x, float vel_y, float vel_z) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnUnoccupiedVehicleUpdate", "(IIIFFFFFF)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, vehicleid, playerid, passenger_seat, new_x, new_y, new_z, vel_x, vel_y, vel_z);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerClickMap(int playerid, float fX, float fY, float fZ) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerClickMap", "(IFFF)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, fX, fY, fZ);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerGiveDamage(int playerid, int damagedid, float  amount, int weaponid, int bodypart) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerGiveDamage", "(IIFII)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, damagedid, amount, weaponid, bodypart);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerTakeDamage(int playerid, int issuerid, float  amount, int weaponid, int bodypart) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerTakeDamage", "(IIFII)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, issuerid, amount, weaponid, bodypart);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerClickPlayerTextDraw(int playerid, int playertextid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerClickPlayerTextDraw", "(II)I");
	int r = 1;
	if (method) {
		r = env->CallStaticIntMethod(callback_class, method, playerid, playertextid);
		checkForExceptions(env);
	}
	return r != 0;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerClickTextDraw(int playerid, int clickedid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerClickTextDraw", "(II)I");
	int r = 1;
	if (method) {
		r = env->CallStaticIntMethod(callback_class, method, playerid, clickedid);
		checkForExceptions(env);
	}
	return r != 0;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerEditAttachedObject(int playerid, int response, int index, int modelid, int boneid, float fOffsetX, float fOffsetY, float fOffsetZ, float fRotX, float fRotY, float fRotZ, float fScaleX, float fScaleY, float fScaleZ) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerEditAttachedObject", "(IIIIIFFFFFFFFF)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, response, index, modelid, boneid, fOffsetX, fOffsetY, fOffsetZ, fRotX, fRotY, fRotZ, fScaleX, fScaleY, fScaleZ);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerSelectObject(int playerid, int type, int objectid, int modelid, float fX, float fY, float fZ) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerSelectObject", "(IIIIFFF)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, type, objectid, modelid, fX, fY, fZ);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerWeaponShot(int playerid, int weaponid, int hittype, int hitid, float fX, float fY, float fZ) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerWeaponShot", "(IIIIFFF)I");
	int r = 1;
	if (method) {
		r = env->CallStaticIntMethod(callback_class, method, playerid, weaponid, hittype, hitid, fX, fY, fZ);
		checkForExceptions(env);
	}
	return r != 0;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnTrailerUpdate(int playerid, int vehicleid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnTrailerUpdate", "(II)I");
	int r = 1;
	if (method) {
		r = env->CallStaticIntMethod(callback_class, method, playerid, vehicleid);
		checkForExceptions(env);
	}
	return r != 0;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerCommandText(int playerid, const char* cmd) {
	if (strlen(cmd) > 128) return false;
	char textDst[(128 * 2) + 1];
	strcpy(textDst, cmd);
	VerifyAndValidateInput(textDst);
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerCommandText", "(ILjava/lang/String;)I");
	int r = 0;
	if (method) {
		jchar wtext[128 + 1];
		int len = mbs2wcs(1252, textDst, -1, wtext, sizeof(wtext) / sizeof(wtext[0]));
		jstring string1 = env->NewString(wtext, len);
		r = env->CallStaticIntMethod(callback_class, method, playerid, string1);
		env->DeleteLocalRef(string1);
		checkForExceptions(env);
	}
	return r != 0;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerText(int playerid, const char* text) {
	if (strlen(text) > 128) return false;
	char textDst[(128 * 2) + 1];
	strcpy(textDst, text);
	VerifyAndValidateInput(textDst);
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerText", "(ILjava/lang/String;)I");
	int r = 1;
	if (method) {
		jchar wtext[128 + 1];
		int len = mbs2wcs(1252, textDst, -1, wtext, sizeof(wtext) / sizeof(wtext[0]));
		jstring string1 = env->NewString(wtext, len);
		r = env->CallStaticIntMethod(callback_class, method, playerid, string1);
		env->DeleteLocalRef(string1);
		checkForExceptions(env);
	}
	return r != 0;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnRconCommand(const char* cmd) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnRconCommand", "(Ljava/lang/String;)I");
	int r = 0;
	if (method) {
		jstring string1 = env->NewStringUTF(cmd);
		r = env->CallStaticIntMethod(callback_class, method, string1);
		env->DeleteLocalRef(string1);
		checkForExceptions(env);
	}
	return r != 0;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnDialogResponse(int playerid, int dialogid, int response, int listitem, const char* inputtext) {
	if (strlen(inputtext) > 128) return false;
	char textDst[(128 * 2) + 1];
	strcpy(textDst, inputtext);
	VerifyAndValidateInput(textDst);
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnDialogResponse", "(IIIILjava/lang/String;)I");
	int r = 0;
	if (method) {
		jchar wtext[128 + 1];
		int len = mbs2wcs(1252, textDst, -1, wtext, sizeof(wtext) / sizeof(wtext[0]));
		jstring string1 = env->NewString(wtext, len);
		r = env->CallStaticIntMethod(callback_class, method, playerid, dialogid, response, listitem, string1);
		env->DeleteLocalRef(string1);
		checkForExceptions(env);
	}
	return r != 0;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnIncomingConnection(int playerid, const char* ip_address, int port) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnIncomingConnection", "(ILjava/lang/String;I)I");
	if (method) {
		jstring string1 = env->NewStringUTF(ip_address);
		env->CallStaticIntMethod(callback_class, method, playerid, string1, port);
		env->DeleteLocalRef(string1);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerEditObject(int playerid, bool playerobject, int objectid, int response, float fX, float fY, float fZ, float fRotX, float fRotY, float fRotZ) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerEditObject", "(IZIIFFFFFF)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, playerobject, objectid, response, fX, fY, fZ, fRotX, fRotY, fRotZ);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerEnterVehicle(int playerid, int vehicleid, bool ispassenger) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerEnterVehicle", "(IIZ)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, vehicleid, ispassenger);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnRconLoginAttempt(const char* ip, const char* password, bool success) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnRconLoginAttempt", "(Ljava/lang/String;Ljava/lang/String;Z)I");
	if (method) {
		jstring string1 = env->NewStringUTF(ip);
		jstring string2 = env->NewStringUTF(password);
		env->CallStaticIntMethod(callback_class, method, string1, string2, success);
		env->DeleteLocalRef(string1);
		env->DeleteLocalRef(string2);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnPlayerGiveDamageActor(int playerid, int damaged_actorid, float amount, int weaponid, int bodypart) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnPlayerGiveDamageActor", "(IIFII)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, damaged_actorid, amount, weaponid, bodypart);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnVehicleSirenStateChange(int playerid, int vehicleid, bool newstate) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnVehicleSirenStateChange", "(IIZ)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, playerid, vehicleid, newstate);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnActorStreamIn(int actorid, int forplayerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnActorStreamIn", "(II)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, actorid, forplayerid);
		checkForExceptions(env);
	}
	return true;
}

PLUGIN_EXPORT bool PLUGIN_CALL OnActorStreamOut(int actorid, int forplayerid) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	static jmethodID method = getMethodAndReportIfNotFound(env, "OnActorStreamOut", "(II)I");
	if (method) {
		env->CallStaticIntMethod(callback_class, method, actorid, forplayerid);
		checkForExceptions(env);
	}
	return true;
}

#ifdef _WIN32
#	include "windows/windirent.h"
#else
#	include <dirent.h>
#endif

vector<string>* getJarFilesInDirectory(const char *directory) {
	vector<string>* result = new vector<string>;
	DIR *dir;
	struct dirent *ent;
	if ((dir = opendir(directory)) != NULL) {
		while ((ent = readdir(dir))) {
			string fileName(ent->d_name);
			if (fileName.rfind(".jar") != -1) {
				result->push_back(fileName);
			}
		}
	}
	else {
		return NULL;
	}
	return result;
}

jmethodID getMethodAndReportIfNotFound(JNIEnv *env, char *name, char *signature) {
	jmethodID result = env->GetStaticMethodID(callback_class, name, signature);
	if (result == NULL) {
		sampgdk::logprintf("[warning] No se encontro el metodo: '%s' sig: '%s'", name, signature);
		env->ExceptionDescribe();
	}
	return result;
}

void checkForExceptions(JNIEnv* env) {
	jthrowable throwable = env->ExceptionOccurred();
	if (throwable) {
		static jmethodID method = getMethodAndReportIfNotFound(env, "OnExceptionOccurred", "(Ljava/lang/Throwable;)V");
		if (method) {
			env->CallStaticVoidMethod(callback_class, method, throwable);
		}
		env->ExceptionClear();
	}
}
