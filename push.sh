#!/bin/bash

cd $(dirname $0)

docker push unterstein/dcos-beer-service:latest
docker push unterstein/dcos-beer-database:latest
