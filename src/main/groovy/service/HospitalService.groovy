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
     * List all the hospitals in the database
     * @return a promise to a list of {@link Hospital} models
     */
    Promise<List<Hospital>> fetchAll()

    /**
     * Saves the provided {@link Hospital} moel
     * @param hospital
     */
    Operation save(Hospital hospital)

}
