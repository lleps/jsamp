# This file will update jsamp.jar and jsamp.dll before start the server.
# Also will run an infinite loop to avoid executing the file everytime you want to start the server.

while true; do
	cp "../api/target/opplus-1.0-SNAPSHOT.jar" "jsamp/jars/"

	echo "setup linux project first. Then, add the directory to binaries to make this file copy the files."
	#cp
	#./samp03svr
	sleep 2
done

