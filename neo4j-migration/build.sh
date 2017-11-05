#!/bin/bash

cd $(dirname $0)

mvn clean install
docker build --tag unterstein/dcos-beer-neo4j-migration:latest .
