#export JRE_HOME=Path to JRE8 (32-bit)

if [ -z "$JRE_HOME" ]; then
	echo "Please edit the startup.sh file and add your JRE installation path to line 1 and remove the # character."
	exit 1
fi

export LD_LIBRARY_PATH=.:$JRE_HOME/lib/i386:$JRE_HOME/lib/i386/client:$JRE_HOME/lib/i386/server:/usr/local/lib
./samp03svr
