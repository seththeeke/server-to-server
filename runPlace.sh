#!/bin/bash

echo --------------------------------------
echo ------------JAX-RS Project------------
echo --------------------------------------

echo starting to build project...
gradle cleanEclipse eclipse build fatJar

start=$1

if [ "$start" = "start" ]; then
    echo starting jax-rs project
	java -jar PlaceServer/build/libs/project-jar.jar
fi