import com.fasterxml.jackson.databind.ObjectMapper
import config.DatabaseConfig
import config.HikariConfigModule
import groovy.sql.Sql
import ratpack.groovy.sql.SqlModule
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.hikari.HikariModule
import ratpack.server.Service
import ratpack.server.StartEvent

import javax.sql.DataSource

import java.sql.Connection;

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {
    bindings {
        module MarkupTemplateModule
        // Initialize SqlModule to provide
        // Groovy SQL support in our application.
        module SqlModule
        module HikariModule
        module HikariConfigModule
        bindInstance new Service() {
            void onStart(StartEvent e) {
                Sql sql = e.registry.get(Sql)
                sql.execute("insert into hospital VALUES ('2','hospitalName')")
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
        get {
            render groovyMarkupTemplate("index.gtpl", title: "My Ratpack App")
        }

        get("test") {
            DataSource dataSource = get(DataSource.class)

        }

        files { dir "public" }
    }
}
