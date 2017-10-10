# The DC/OS beer demo

You wonder how [Java](), [Spring-Boot](https://spring.io), [MySQL](https://mysql.com), [Neo4J](https://neo4j.com), [Zeppelin](), [Spark](), [Elasticsearch](https://elastic.co) and [DC/OS](https://dcos.io) fits in one demo? Well, we`ll show you!

## The domain
In this demo we are all talking about beer. There is this old website [openbeerdb.com](https://openbeerdb.com) and they offer a quite old list of beer, brewery, beer categories and beer styles as downloadable sql databases. You can find this sql files and the related readme in `database/sql`. This database contains a list of around 1500 breweries and 6000 different beers with their relation to category and style. You can see the important properties in the model below:

![Model](images/or.png)

