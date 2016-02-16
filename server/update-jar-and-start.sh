# This file will update jsamp.jar from api directory then start the server.

while true; do
	cp "../api/target/opplus-1.0-SNAPSHOT.jar" "jsamp/jars/"
	./samp03svr
	sleep 2
done

