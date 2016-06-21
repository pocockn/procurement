package service

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Inject
import database.JsonObjectMapper
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import jdk.nashorn.internal.ir.annotations.Ignore
import model.Product
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.exec.Promise

/**
 * Created by pocockn on 09/06/16.
 */
@Ignore
class StorageServiceHospitalImplementation implements StorageService<Product> {

    @Inject
    Sql sql
    @Inject
    ObjectMapper mapper

    @Override
    Promise<List<Product>> fetchAll() {
        Blocking.get {
            sql.rows("select * from products").collect { GroovyRowResult result ->
                String instanceJson = result.getAt(1)
                Product instance = mapper.readValue(instanceJson, Product)
                return instance
            }
        }
    }

    @Override
    Operation save(Product product) {
        Blocking.get {
            sql.execute "INSERT INTO products (id, hospital_information) VALUES (${hospital.id}, ${JsonObjectMapper.mapObjectToJson(hospital)})"
        }.operation()
    }

    @Override
    Promise<List<Product>> fetchByID(String id) {
        Blocking.get {
            sql.rows("SELECT * FROM products WHERE id = ${id}").collect { GroovyRowResult result ->
                // getAt(1) will get us the second column with all the content in
                String instanceJson = result.getAt(1)
                Product instance = mapper.readValue(instanceJson, Product)
                return instance
            }
        }
    }

    @Override
    Operation delete(String id) {
        Blocking.get {
            sql.execute "DELETE FROM products WHERE id = ${id}"
        }.operation()
    }
}
