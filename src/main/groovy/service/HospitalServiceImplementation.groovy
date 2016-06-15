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
    private final ObjectMapper mapper

    /*
     * Using Google Guice to inject our SQL and object mapper from our ratpack registry
     */
    @Inject
    HospitalServiceImplementation(Sql sql, ObjectMapper mapper) {
        this.sql = sql
        this.mapper = mapper
    }

    @Override
    Promise<List<Hospital>> fetchAll() {
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

    @Override
    Promise<List<Hospital>> fetchByName(String name) {
        Blocking.get {
            sql.rows("SELECT * FROM hospitals WHERE name = ${name}").collect { GroovyRowResult result ->
                String instanceJson = result.getAt(1)
                println instanceJson
                Hospital instance = mapper.readValue(instanceJson, Hospital)
                instance
            }
        }
    }

    @Override
    Operation delete(String id) {
        Blocking.get {
            sql.execute "DELETE FROM hospitals WHERE id = ${id}"
        }.operation()
    }
}
