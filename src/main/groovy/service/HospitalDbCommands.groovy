package service

import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import model.Hospital
import ratpack.exec.Blocking
import ratpack.exec.Promise
import com.google.inject.Inject
import ratpack.registry.Registry

import java.sql.Timestamp

/**
 * Created by pocockn on 08/06/16.
 */
class HospitalDbCommands {

    private final Sql sql

    @Inject
    public HospitalDbCommands(Sql sql) {
        this.sql = sql
    }

    public Promise<List<GroovyRowResult>> list() {
        Blocking.get {
            sql.rows("select id from hospitals")
        }

    }

}
