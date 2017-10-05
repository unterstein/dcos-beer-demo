#!/bin/bash

cd $(dirname $0)

docker build --tag unterstein/dcos-beer-database:latest .
