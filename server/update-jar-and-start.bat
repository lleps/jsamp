:: This file will update jsamp.jar from api directory then start the server.

:start

copy "..\api\target\jsamp-1.0-SNAPSHOT.jar" "jsamp\jars\"

samp-server.exe

pause

goto start