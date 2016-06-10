package handlers

import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.handling.InjectionHandler

/**
 * Created by pocockn on 10/06/16.
 */
class HospitalsHandler implements Handler{

    void handle(Context ctx) {
        ctx.byMethod { m ->
            m.get {

            }
        }
    }
}
