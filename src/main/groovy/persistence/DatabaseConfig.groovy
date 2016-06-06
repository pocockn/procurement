package persistence

import groovy.sql.Sql

/**
 * Created by pocockn on 06/06/16.
 */
class DatabaseConfig {

    String dbUrl
    String dbUser
    String dbPassword
    String dbDriver = "org.postgresql.Driver"

    public databaseConnection() {
        return Sql.newInstance(this.dbUrl,this.dbUser,this.dbPassword,this.dbDriver)
    }
}
