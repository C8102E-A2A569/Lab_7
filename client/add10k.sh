#!/usr/bin/env bash

USER=test
PASSWORD=ttt

java -jar build/libs/client-1.0-SNAPSHOT.jar execute_script add5k "$USER" "$PASSWORD" &
java -jar build/libs/client-1.0-SNAPSHOT.jar execute_script add5k "$USER" "$PASSWORD" &
java -jar build/libs/client-1.0-SNAPSHOT.jar
