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
    Promise<List<Hospital>> fetchAll() {
        ObjectMapper mapper = new ObjectMapper()
        Blocking.get {
            sql.rows("select * from hospitals").collect { GroovyRowResult result ->
                String instanceJson = result.getAt(1)
                println instanceJson
                Hospital instance = mapper.readValue(instanceJson, Hospital)
                instance
            }
        }
    }

    @Override
    Operation save(Hospital hospital) {
        Blocking.get {
            sql.execute "INSERT INTO hospitals (id, hospital_information) VALUES (${hospital.id}, ${JsonObjectMapper.mapObjectToJson(hospital)})"
        }.operation()
    }
}
