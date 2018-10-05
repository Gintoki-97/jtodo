#!/bin/bash

su - jesus

cd /home/jesus/developkit/repository/git/jtodo

# update the code from git
git pull -r

cd /home/jesus/developkit/service/tomcat/jtodo/t1/bin
./shutdown.sh

# copy the execution jar file to the tomcat
rm -rf /home/jesus/developkit/service/tomcat/jtodo/t1/webapps/*
cp /home/jesus/developkit/repository/git/jtodo/src/web/target/jtodo*.jar /home/jesus/developkit/service/tomcat/jtodo/t1/webapps/jtodo.jar

./start.sh