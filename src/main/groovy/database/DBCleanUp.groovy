package database

import com.google.inject.Inject
import groovy.sql.Sql

/**
 * Created by pocockn on 09/06/16.
 */
class DBCleanUp {

    @Inject
    Sql sql

    void truncateDB() {
        try {
            sql.executeUpdate("truncate hospitals")
        }
        finally {
            sql?.close()
        }
    }
}
