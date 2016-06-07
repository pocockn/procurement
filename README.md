Procurement Project Prototype

An application in development using Ratpack, Groovy, Geb and Spock.

To run open in Intellij and run ./gradlew build

Postgres Steps

Login to Postgres

_sudo su - postgres_

Create the DB

_psql >> CREATE DATABASE dbName;_

Create user with super privileges

_createuser name_of_user_

Login to Postgres prompt with

_psql_

Show roles

_\du_

Add super privileges

_ALTER ROLE role_name WITH attribute_options;_

Add a password 

_\password test_user_

Login to the database 

psql -U username -d dbName -h 127.0.0.1 -W