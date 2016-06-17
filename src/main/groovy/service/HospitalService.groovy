package service

import model.Hospital
import model.HospitalSummary
import ratpack.exec.Operation
import ratpack.exec.Promise

/**
 * Created by pocockn on 08/06/16.
 */

interface HospitalService {

    Promise<List<Hospital>> fetchByName(String name)

    Promise<List<Hospital>> fetchAll()

    Operation save(Hospital hospital)

    Operation delete(String id)

}
