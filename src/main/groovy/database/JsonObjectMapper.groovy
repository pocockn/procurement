package database

import com.fasterxml.jackson.databind.ObjectMapper
import model.Hospital

/**
 * Created by pocockn on 10/06/16.
 */
class JsonObjectMapper<T> {

    static String mapObjectToJson(T obj) {
        ObjectMapper objectMapper = new ObjectMapper()
        String jsonObject = objectMapper.writeValueAsString(obj)
        return jsonObject
    }

    static Hospital mapJsonToObject(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper()
        Hospital hospital = objectMapper.readValue(jsonString, Hospital.class)
        return hospital

    }
}
