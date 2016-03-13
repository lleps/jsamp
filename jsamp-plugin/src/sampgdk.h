<?xml version="1.0" encoding="UTF-8" standalone="yes" ?>
<CodeBlocks_project_file>
	<FileVersion major="1" minor="6" />
	<Project>
		<Option title="jsamp-plugin" />
		<Option pch_mode="2" />
		<Option compiler="gcc" />
		<Build>
			<Target title="Debug">
				<Option output="bin/linux/Debug/jsamp-plugin" prefix_auto="1" extension_auto="1" />
				<Option object_output="obj/Debug/" />
				<Option type="3" />
				<Option compiler="gcc" />
				<Option createDefFile="1" />
				<Option createStaticLib="1" />
				<Compiler>
					<Add option="-g" />
				</Compiler>
			</Target>
			<Target title="Release">
				<Option output="bin/linux/Release/jsamp-plugin" prefix_auto="1" extension_auto="1" />
				<Option object_output="obj/Release/" />
				<Option type="3" />
				<Option compiler="gcc" />
				<Option createDefFile="1" />
				<Option createStaticLib="1" />
				<Compiler>
					<Add option="-O2" />
				</Compiler>
				<Linker>
					<Add option="-s" />
				</Linker>
			</Target>
		</Build>
		<Compiler>
			<Add option="-Wall" />
			<Add option="-fexceptions" />
			<Add option="-DSAMPGDK_AMALGAMATION" />
			<Add directory="/opt/libiconv-1.14/include" />
			<Add directory="/opt/jdk1.8.0_45/include" />
			<Add directory="/opt/jdk1.8.0_45/include/linux" />
			<Add directory="./lib" />
			<Add directory="./lib/sampsdk" />
		</Compiler>
		<Linker>
			<Add library="/usr/local/lib/libiconv.so" />
			<Add library="/opt/jdk1.8.0_45/jre/lib/i386/server/libjvm.so" />
			<Add library="/opt/jdk1.8.0_45/jre/lib/i386/server/libjsig.so" />
		</Linker>
		<Unit filename="src/EncodingUtils.cpp" />
		<Unit filename="src/EncodingUtils.h" />
		<Unit filename="src/FileUtils.cpp" />
		<Unit filename="src/FileUtils.h" />
		<Unit filename="src/JNISampFunctions.cpp" />
		<Unit filename="src/JNISampFunctions.h" />
		<Unit filename="src/Main.cpp" />
		<Unit filename="src/sampgdk.c">
			<Option compilerVar="CC" />
		</Unit>
		<Unit filename="src/sampgdk.h" />
		<Unit filename="src/windirent.h" />
		<Extensions>
			<code_completion />
			<debugger />
		</Extensions>
	</Project>
</CodeBlocks_project_file>
