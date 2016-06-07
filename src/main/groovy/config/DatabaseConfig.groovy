package config
/**
 * Created by pocockn on 06/06/16.
 */
class DatabaseConfig {

    String dbHost = "127.0.0.1"
    String dbUser = "pocockn"
    String db = "surgery"
    String dbPassword
    Integer port = 5432

    String getJdbcUrl() {
        "jdbc:postgresql://${getDbHost()}:${getPort()}/${getDb()}"
    }


}
