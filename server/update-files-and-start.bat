:: This file will update jsamp.jar and jsamp.dll before start the server.
:: Also will run an infinite loop to avoid executing the file everytime you want to start the server.

:start

set "PluginBuildType=Release"
:: Release or Debug

copy "..\api\target\jsamp-1.0-SNAPSHOT.jar" "jsamp\jars\"
copy "..\jsamp-plugin\Debug\jsamp-plugin.dll" "plugins\jsamp.dll"

startup.bat

pause

goto start