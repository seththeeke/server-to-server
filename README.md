# server-to-server
Project where two servers speak to each other to access data

## About
There are two projects. A human service and a place service. The data for each service is only accessable through the "database"(hashmap) that belongs to the service. So, in order to access place related data through the human server port, the human service sends a request to the place service. For example, a user can access the human data via curl http://localhost:8080/humans OR through curl http://localhost:8090/humans however going through port 8090 will require an additional internal request between the two servers.<br>

## Build
Java 8 - v1.8.0_92 or later<br>
Gradle build tool<br>

Ensure you have gradle installed on your machine(https://gradle.org/install/) and navigate to the base project directory. You can control the server properties by placing a properties file called serverConfig.properties in your /etc/properties directory. The properties file example also exists in the Interfaces/src/main/resources directory of this project.<br>

To run:<br>
<code>Run $ chmod 700 runPlace.sh</code><br>
<code>Run $ chmod 700 runHuman.sh</code><br>
<code>Run $ gradle cleanEclipse eclipse build fatJar to build</code><br>
OR<br>
<code>Run $ ./runPlace.sh to start the Place Service</code><br>
Open a new Terminal<br>
<code>Run $ ./runHuman.sh to start the Human Service</code><br>

## Testing
4 requests are valid. They will work on both server ports. The ids of the objects are either 1 or 2
Postman returns the values in the "prettiest" forms.<br> 
<code>$ curl http://localhost:8080/places</code><br>
<code>$ curl http://localhost:8080/humans</code><br>
<code>$ curl http://localhost:8080/places/{placeId}</code><br>
<code>$ curl http://localhost:8080/humans/{humanId}</code><br><br>

<code>$ curl http://localhost:8090/places</code><br>
<code>$ curl http://localhost:8090/humans</code><br>
<code>$ curl http://localhost:8090/places/{placeId}</code><br>
<code>$ curl http://localhost:8090/humans/{humanId}</code><br>
