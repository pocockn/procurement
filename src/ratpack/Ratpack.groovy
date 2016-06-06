import com.fasterxml.jackson.databind.ObjectMapper
import persistence.DatabaseConfig
import ratpack.groovy.template.MarkupTemplateModule

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {
    bindings {
        module MarkupTemplateModule
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
        get("config") {
            DatabaseConfig config -> render mapper.writeValueAsString(config)
        }
        get {
            render groovyMarkupTemplate("index.gtpl", title: "My Ratpack App")
        }

        files { dir "public" }
    }
}
