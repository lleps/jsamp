# This file will update jsamp.jar and jsamp.dll before start the server.
# Also will run an infinite loop to avoid executing the file everytime you want to start the server.

while true; do
	cp "../api/target/jsamp-1.0-SNAPSHOT.jar" "jsamp/jars/"
	cp "../jsamp-plugin/bin/linux/Release/libjsamp-plugin.so" "plugins/jsamp"
	
	./startup.sh

	sleep 2
done

