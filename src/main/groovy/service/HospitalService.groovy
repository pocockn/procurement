package service

import model.Hospital
import model.HospitalSummary
import ratpack.exec.Operation
import ratpack.exec.Promise

/**
 * Created by pocockn on 08/06/16.
 */

interface HospitalService {

    /**
     * Fetch a Hospital by it's name
     * @return a promise to a list of matching {@link Hospital} models
     */
    Promise<List<Hospital>> fetchByName(String name)
    
    /**
     * List all the hospitals in the database
     * @return a promise to a list of {@link Hospital} models
     */
    Promise<List<Hospital>> fetchAll()

    /**
     * Saves the provided {@link Hospital} moel
     * @param hospital
     */
    Operation save(Hospital hospital)

    Operation delete(String id)

}
