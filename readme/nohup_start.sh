#!/bin/sh
tomcat="smas-mirror-migrate"
pkill -9 -f $tomcat

if [ "$1" == "k" ];then
        echo "程序已杀死"
        exit 1
fi

sleep 3
tomcatPid=$(pgrep -f $tomcat)
if [ ! -z "$tomcatPid" ];then
        echo "程序未杀死"
        exit 1
fi

export MEM_ARGS="-Xms1G -Xmx4G -XX:ReservedCodeCacheSize=1G -XX:+UseConcMarkSweepGC"
mv logs/log.out logs/log.out_$(date "+%Y-%m-%d-%H:%M:%S")
nohup java $MEM_ARGS -jar smas-mirror-migrate-1.0.jar>logs/log.out &
tailf logs/log.out