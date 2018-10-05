#!/bin/bash

user=`whoami`

if [ $user = "jesus" ]

then
    cd /home/jesus/developkit/repository/git/jtodo
    
    # update the code from git
    git pull -r

    if [ $? -eq 0 ]

    then
        sh /home/jesus/developkit/service/tomcat/jtodo/t1/bin/shutdown.sh
        
        rm -rf /home/jesus/developkit/service/tomcat/jtodo/t1/webapps/*
        cp /home/jesus/developkit/repository/git/jtodo/src/web/target/jtodo*.war /home/jesus/developkit/service/tomcat/jtodo/t1/webapps/jtodo.war
            
        sh /home/jesus/developkit/service/tomcat/jtodo/t1/bin/startup.sh
    else
        echo "Git pull -r failed"
    fi
else
    echo "Please execute as jesus"
fi
