#!/bin/bash

user = `whoami`

if [ $user = "jesus" ]; then

    cd /home/jesus/developkit/repository/git/jtodo
    
    # update the code from git
    git pull -r

    if [ $? -ne 0]; then
        cd "/home/jesus/developkit/service/tomcat/jtodo/t1/bin"
        ../shutdown.sh
        
        rm -rf /home/jesus/developkit/service/tomcat/jtodo/t1/webapps/*
        cp /home/jesus/developkit/repository/git/jtodo/src/web/target/jtodo*.war /home/jesus/developkit/service/tomcat/jtodo/t1/webapps/jtodo.war
            
        cd /home/jesus/developkit/service/tomcat/jtodo/t1/bin
        ../start.sh
    else
        echo "Git pull -r failed"
else
    echo "Please execute as jesus"
