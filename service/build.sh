#!/bin/bash

cd $(dirname $0)

mvn clean install -DskipTests
docker build --tag unterstein/dcos-beer-service:latest .
