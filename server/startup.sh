
if [ -z "$JRE_HOME" ]; then
	echo "Please JRE_PATH environment variable."
	exit 1
fi

export LD_LIBRARY_PATH=.:$JRE_HOME/lib/i386:$JRE_HOME/lib/i386/client:$JRE_HOME/lib/i386/server:/usr/local/lib
./samp03svr
