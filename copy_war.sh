#!/bin/bash
BASE_PATH=/cygdrive/c/Development
WAR_PATH=${BASE_PATH}/repos/infinispan-tester/target/infinispan-tester.war
SRV_PATH=${BASE_PATH}/servers/apache-tomcat-7.0.39

for path in "/" "_2/";
do
 echo "Shuttting down server: ${SRV_PATH}${path}"
 ${SRV_PATH}${path}bin/shutdown.sh
 
 echo "Removing ${SRV_PATH}${path}webapps/infinispan-tester*"
 rm -rf ${SRV_PATH}${path}webapps/infinispan-tester*
 
 echo "Copying file: ${WAR_PATH} to directory: ${path}"
 cp ${WAR_PATH} ${SRV_PATH}${path}webapps/
 
 echo "Starting ${SRV_PATH}${path}bin/startup.sh"
 ${SRV_PATH}${path}bin/startup.sh
done