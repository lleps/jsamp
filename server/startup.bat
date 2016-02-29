:: Script to start the server.
:: From shoebill project.

@echo off

IF "%JRE_HOME%" == "" (
	echo Please set JRE_HOME environment variable to your jre installation. Use google if you dont know how to do it.
	pause
	exit
)

set "JVM_SERVER_LIB_PATH=%JRE_HOME%\bin\server"
set "JVM_CLIENT_LIB_PATH=%JRE_HOME%\bin\client"

IF EXIST "%JVM_SERVER_LIB_PATH%" (
  echo Server VM found.
  set "PATH=%JVM_SERVER_LIB_PATH%;%PATH%"
) ELSE IF EXIST "%JVM_CLIENT_LIB_PATH%" (
  echo Server VM not found, using client VM..
  set "PATH=%JVM_CLIENT_LIB_PATH%;%PATH%"
) ELSE (
  echo JVM not found.
  pause
  exit
)

samp-server.exe