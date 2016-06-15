import config.HikariConfigModule
import groovy.sql.Sql
import handlers.AddHospitalHandler
import ratpack.groovy.sql.SqlModule
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.handlebars.HandlebarsModule
import ratpack.hikari.HikariModule
import ratpack.service.Service
import ratpack.service.StartEvent
import service.HospitalService
import service.HospitalServiceImplementation

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
        bind HospitalService, HospitalServiceImplementation
        bindInstance new Service() {
            void onStart(StartEvent e) {
                Sql sql = e.registry.get(Sql)
            }
        }

    }

    handlers {
        get { HospitalService hospitalService ->
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

        post("delete/:id") {
            def id = pathTokens["id"]
        }

        files { dir "public" }
    }
}
