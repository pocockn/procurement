package service

import com.google.inject.Inject
import groovy.sql.Sql
import model.Hospital
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.exec.Promise

/**
 * Created by pocockn on 09/06/16.
 */
class HospitalServiceImplementation implements HospitalService{

    private final Sql sql

    @Inject
    HospitalServiceImplementation(Sql sql) {
        this.sql = sql
    }

    @Override
    Promise<List<Hospital>> list() {
        Blocking.get {
            sql.rows("select * from hospitals").collect {
                def id = (UUID)it["id"]
                new Hospital(id: id)
            }
        }
    }

    @Override
    Operation save(Hospital hospital) {
        Blocking.get {
            sql.execute "INSERT INTO hospitals (id,name) VALUES (${hospital.id}, ${hospital.name})"
        }.operation()
    }
}
