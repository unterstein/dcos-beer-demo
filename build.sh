#!/bin/bash

cd $(dirname $0)

./service/build.sh
./database/build.sh
./neo4j-migration/build.sh
