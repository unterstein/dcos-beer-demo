# The DC/OS beer demo

You wonder how [Java](), [Spring Boot](https://spring.io), [MySQL](https://mysql.com), [Neo4J](https://neo4j.com), [Zeppelin](), [Spark](), [Spark](), [Elasticsearch](https://elastic.co), [Docker]() and [DC/OS](https://dcos.io) fits in one demo? Well, we'll show you! This is a rather complex demo, so grab your favorit beer and enjoy 🍺

## The domain
In this demo we are all talking about beer. There is this old website [openbeerdb.com](https://openbeerdb.com) and they offer a quite old list of beer, brewery, beer categories and beer styles as downloadable sql databases. You can find this sql files and the related readme in `database/sql`. This database contains a list of around 1500 breweries and 6000 different beers with their relation to category and style. You can see the important properties in the model below:

![Model](images/or.png)

## Overview
Ok, now you know the domain: Let's talk about our story! 

1. In this demo we want to build a simple Spring Boot service first which offers us a random beer of the day via HTTP API. To service this purpose, you can find a spring  boot web application in the `service` folder. We see all the application lifecycle steps here from deploy over scale to break, update and recover.
2. After we successfully implemented our service using the openbeerdb data, we want to do more analysis. For this reason we are using the Neo4j graph database. You can find the data migration implementation in the `migration` folder. 
3. Using the power of a graph database, we can do fancy queries like `List all styles of beers produced in a certain and show many breweries produce it there` without the hassle of complicated joins.
4. After we did complex analysis in a graph database, we want to do mass analysis using a map/reduce job in Spark. Therefore you can find a Zeppelin notepad in the file `zeppelin-analysis.json` containing a map/reduce job to count all the words in beer descriptions to give an overview of what words are used in a beer description.
5. Last but not least we will see how use Elasticsearch to back our service architecture with a centralized logging system.

## 0. Docker
We are using Docker in this demo to package most parts of our system. This is important because we want to run exactly the same code in development, stage and production. We just want to change the environment specific configuration for each of those stages. 
You can find the Dockerfiles in the `database` and `service` folders. Both images are rather simple and starting with a official base image of `mysql` or `java` and just adding the initial sql files or the application jar file.

Our docker compose configuration looks like this:

```
version: '2'
services:

  database:
    image: unterstein/dcos-beer-database:latest

  service:
    image: unterstein/dcos-beer-service:latest
    ports:
      - "8080:8080"
    depends_on:
      - database
    environment:
      - SERVICE_VERSION=2
      - SPRING_DATASOURCE_URL=jdbc:mysql://database:3306/beer?user=good&password=beer
```

You can see a database without configuration in the first section. In the second section you can see the configured java service. The service depends on the database and has environment specific configuration for it's version and the database connectivity.

