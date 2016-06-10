package service

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Inject
import database.JsonObjectMapper
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import model.Hospital
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.exec.Promise

import static ratpack.jackson.Jackson.json

/**
 * Created by pocockn on 09/06/16.
 */
class HospitalServiceImplementation implements HospitalService {

    private final Sql sql

    @Inject
    HospitalServiceImplementation(Sql sql) {
        this.sql = sql
    }

    @Override
    Promise<List<GroovyRowResult>> list() {
        Blocking.get {
            sql.rows("select * from hospitals")
        }
    }

    @Override
    Operation save(Hospital hospital) {
        Blocking.get {
            sql.execute "INSERT INTO hospitals (id, hospital_information) VALUES (${hospital.id}, ${JsonObjectMapper.mapObjectToJson(hospital)})"
        }.operation()
    }
}
