package service

import model.Hospital
import ratpack.exec.Operation
import ratpack.exec.Promise

/**
 * Created by pocockn on 08/06/16.
 */

interface HospitalService {

    Promise<List<Hospital>> fetchByID(String id)

    Promise<List<Hospital>> fetchAll()

    Operation save(Hospital hospital)

    Operation delete(String id)

}
