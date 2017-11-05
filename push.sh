#!/bin/bash

docker push unterstein/dcos-beer-service:latest
docker push unterstein/dcos-beer-database:latest
docker push unterstein/dcos-beer-neo4j-migration:latest
docker push unterstein/dcos-beer-elasticsearch-migration:latest
