# SQL Exporter for Prometheus
This application is capable of executing queries via JDBC and exposing results as Prometheus feed.

## Distribution
Exporter is distributed as JAR file. Current version is stored in ``dist`` directory.
 
## Configuration
In order to configure exporter create ``application.yml`` file in the directory where you place jar file. Below you will
find an example of the file.

```
sql-exporter.probes:
  - name: db_users_total
    url: jdbc:mysql://127.0.0.1:3306/test_database
    user: database_user
    password: user_password
    query: select count(*) from users

  - name: db_items_total
    url: jdbc:mysql://127.0.0.1:3306/test_database
    user: database_user
    password: user_password
    query: select count(*) from items
``` 

This configuration file will setup and expose two metrics ``db_users_total`` and ``db_items_total``. Values for those 
metrics will be produced by connecting to the database specified in ``url`` connection string with ``user`` and ``password``
credentials and executing ``query`` query.

## Including database drivers
You must manually download jdbc drivers for the database you plan to use.

## Running
In order to run exporter use the following command:
``java -Dloader.path=lib -jar sql-exporter-0.0.1-SNAPSHOT.jar
``

Java will look for all drivers (jar files) to load from ``lib`` directory. You can alternatively specify one or more files
to load. 