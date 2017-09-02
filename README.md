# server-to-server
Simple Project where two servers speak to each other to access data

## About
There are two projects. A human service and a place service. The data for each service is only accessable through the "database"(hashmap) that belongs to the service. So, in order to access place related data through the human server port, the human service sends a request to the place service. For example, a user can access the human data via curl http://localhost:8080/humans OR through curl http://localhost:8090/humans however going through port 8090 will require an additional internal request between the two servers.

## Build
Java 8 - v1.8.0_92 or later
Gradle build tool

Ensure you have gradle installed on your machine(https://gradle.org/install/) and navigate to the base project directory. You can control the server properties by placing a properties file called serverConfig.properties in your /etc/properties directory. The properties file example also exists in the Interfaces/src/main/resources directory of this project

To run:
Run $ chmod 700 runPlace.sh
Run $ chmod 700 runHuman.sh
Run $ gradle cleanEclipse eclipse build fatJar to build
OR
Run $ ./runPlace.sh to start the Place Service
Run $ ./runHuman.sh to start the Human Service

## Testing
4 requests are valid. They will work on both server ports. The ids of the objects are either 1 or 2
Postman returns the values in the "prettiest" forms. 
curl http://localhost:8080/places
curl http://localhost:8080/humans
curl http://localhost:8080/places/{placeId}
curl http://localhost:8080/humans/{humanId}

curl http://localhost:8090/places
curl http://localhost:8090/humans
curl http://localhost:8090/places/{placeId}
curl http://localhost:8090/humans/{humanId}