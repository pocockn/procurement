package service

import com.google.inject.Inject
import model.Hospital
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
    Promise<List<String>> list()

    /**
     * Saves the provided {@link Hospital} moel
     * @param hospital
     */
    Operation save(Hospital hospital)

}
