#!/bin/bash

REPOSITORY=/home/kim/spring-crud/app
PROJECT_NAME=crud

cd $REPOSITORY/$PROJECT_NAME/

echo "> Git Pull"

git pull

echo "> Start Project Build"

./gradlew build

echo "> Change Directory"

cd $REPOSITORY

echo "> Copy build files"

cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/

echo "> Check Current Application's PID"

CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.jar)

echo "> Current Application's PID : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> Application is not running now."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> New Application Deploy!"

JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)

echo "> JAR name : $JAR_NAME"

nohup java -jar $REPOSITORY/$JAR_NAME 2>&1 &