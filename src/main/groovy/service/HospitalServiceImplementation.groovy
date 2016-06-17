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

    @Inject Sql sql
    @Inject ObjectMapper mapper

    @Override
    Promise<List<Hospital>> fetchAll() {
        Blocking.get {
            sql.rows("select * from hospitals").collect { GroovyRowResult result ->
                String instanceJson = result.getAt(1)
                println instanceJson
                Hospital instance = mapper.readValue(instanceJson, Hospital)
                return instance
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
    Promise<List<Hospital>> fetchByID(String id) {
        Blocking.get {
            sql.rows("SELECT * FROM hospitals WHERE id = ${id}").collect { GroovyRowResult result ->
                // getAt(1) will get us the second column with all the content in
                String instanceJson = result.getAt(1)
                println instanceJson
                Hospital instance = mapper.readValue(instanceJson, Hospital)
                return instance
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
