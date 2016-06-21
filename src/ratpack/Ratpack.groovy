import config.HikariConfigModule
import handlers.AddHospitalHandler
import ratpack.groovy.sql.SqlModule
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.handlebars.HandlebarsModule
import ratpack.hikari.HikariModule
import ratpack.service.Service
import ratpack.service.StartEvent
import service.StorageService
import service.StorageServiceHospitalImplementation

import java.util.logging.Logger

import static ratpack.groovy.Groovy.ratpack
import static ratpack.handlebars.Template.handlebarsTemplate

ratpack {
    bindings {

        module MarkupTemplateModule
        module HandlebarsModule
        // Initialize SqlModule to provide
        // Groovy SQL support in our application.
        bind AddHospitalHandler
        module SqlModule
        module HikariModule
        module HikariConfigModule
        bind StorageService, StorageServiceHospitalImplementation
        bindInstance new Service() {
            void onStart(StartEvent e) throws Exception {
                Logger logger = Logger.getLogger("")
                logger.info("Initialising Procurement Project")
            }
        }

    }

    handlers { StorageService hospitalService ->
        get {
            hospitalService.fetchAll().then { hospitals ->
                render handlebarsTemplate("hospitals.html", model: [created: hospitals])
            }
        }

        prefix("api") {
            get("addHospital") {
                render handlebarsTemplate("add-new-hospital.html")
            }
            path "hospitals", AddHospitalHandler
        }

        get('hospital/:id') {
            def id = pathTokens["id"]
            hospitalService.fetchByID(id).then { hospitalSingle ->
                render handlebarsTemplate("single-hospital.html", model: [singleHospital: hospitalSingle])
            }
        }

        post("delete/:id") {
            def id = pathTokens["id"]
            hospitalService.delete(id).then {
                redirect "/?msg=Hospital+$id+delete"
            }
        }

        path("update/:id") {
            def id = pathTokens["id"]

            hospitalService.fetchByID(id)
        }

        files { dir "public" }
    }

}
