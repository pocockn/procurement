import com.fasterxml.jackson.databind.ObjectMapper
import config.DatabaseConfig
import config.HikariConfigModule
import groovy.sql.Sql
import handlers.AddHospitalHandler
import model.Hospital
import ratpack.exec.Blocking
import ratpack.form.Form
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

    /* serverConfig block - specify sources of configuration and map the config into our config model */
    serverConfig {
        // specify the source of the configuration
        json "dbconfig.json"
        // Map the /database configuration path to our model object
        // Filled by the config system, the DatabaseConfig class will become usable throughout our application
        require("/database", DatabaseConfig)
    }

    ObjectMapper mapper = new ObjectMapper();

    handlers {
//        get("config") {
//            // this shows our object is mapped our configuration settings
//            DatabaseConfig config -> render mapper.writeValueAsString(config)
//        }
        get { HospitalService hospitalService ->
            hospitalService.list().then { hospitalList ->
                render(json(hospitalList))
            }
        }

        get("test") { Sql sql ->
            Blocking.get {
                sql.rows("select * from hospitals").collect {
                    it
                }
            } then { ids ->
                render(json(ids))
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
