package handlers

import ratpack.handling.Context
import ratpack.handling.Handler
import service.StorageService

/**
 * Created by pocockn on 10/06/16.
 */
class HospitalsHandler implements Handler {

    protected final StorageService hospitalService


    void handle(Context ctx) {
        ctx.byMethod { m ->
            m.get {

            }
        }
    }
}
