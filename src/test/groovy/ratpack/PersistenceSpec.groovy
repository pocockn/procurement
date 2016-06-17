package ratpack;

import com.google.inject.Inject;
import com.zaxxer.hikari.HikariDataSource;
import groovy.sql.Sql;
import spock.lang.AutoCleanup;
import spock.lang.Specification;

/**
 * Created by pocockn on 16/06/16.
 */
abstract class PersistenceSpec extends Specification{

    @AutoCleanup
    @Inject
    HikariDataSource hikariDataSource

}
