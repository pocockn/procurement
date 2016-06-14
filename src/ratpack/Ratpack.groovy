import com.fasterxml.jackson.annotation.JacksonInject
import config.HikariConfigModule
import groovy.sql.Sql
import handlers.AddHospitalHandler
import ratpack.exec.Blocking
import ratpack.groovy.sql.SqlModule
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.handlebars.HandlebarsModule
import ratpack.hikari.HikariModule
import ratpack.jackson.Jackson
import ratpack.service.Service
import ratpack.service.StartEvent
import service.HospitalService
import service.HospitalServiceImplementation

import static ratpack.groovy.Groovy.ratpack
import static ratpack.handlebars.Template.handlebarsTemplate
import static ratpack.jackson.Jackson.json

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

        get("test") { Sql sql ->
            Blocking.get {
                sql.rows("select * from hospitals")
            } then { result ->
                def newList = result.collect { singleHospital ->
                    singleHospital.id
                }
                render(json(newList))
            }
        }

        prefix("api") {
            get("addHospital") {
                render handlebarsTemplate("add-new-hospital.html")
            }
            path "hospitals/:id?", AddHospitalHandler
        }

        files { dir "public" }
    }
}
