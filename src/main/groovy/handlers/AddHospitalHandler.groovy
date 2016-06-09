package handlers

import model.Hospital
import ratpack.form.Form
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.HospitalService

import static ratpack.handlebars.Template.handlebarsTemplate
import static ratpack.jackson.Jackson.json
import static ratpack.jackson.Jackson.json
import static ratpack.jackson.Jackson.json

/**
 * Created by pocockn on 09/06/16.
 */
class AddHospitalHandler extends InjectionHandler {

    void handle(Context ctx, HospitalService hospitalService) {
        ctx.byMethod { method ->
            method.post {
                ctx.parse(Form). then { form ->
                    def name = form.name
                    if (name) {
                        def id = UUID.randomUUID()
                        def hospital = new Hospital(id: id, name: name)
                        hospitalService.save(hospital).onError { error ->
                            ctx.render json([success: false, error: error.message])
                        } then {
                            ctx.render handlebarsTemplate("added-new.html")
                        }
                    } else {
                        ctx.response.status(400)
                        ctx.render(json([success: false, error: "name is required"]))
                    }
                }
            }
        }
    }
}
