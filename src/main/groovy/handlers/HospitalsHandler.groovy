package handlers

import model.Hospital
import model.HospitalSummary
import ratpack.exec.Promise
import ratpack.handling.Context
import ratpack.handling.Handler
import service.HospitalService

/**
 * Created by pocockn on 10/06/16.
 */
class HospitalsHandler implements Handler {

    protected final HospitalService hospitalService


    void handle(Context ctx) {
        ctx.byMethod { m ->
            m.get {

            }
        }
    }
}
