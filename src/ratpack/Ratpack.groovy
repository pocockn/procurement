import ratpack.groovy.template.MarkupTemplateModule

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack
import persistence.DatabaseConfig


ratpack {
  bindings {
    module MarkupTemplateModule
  }
  serverConfig {
    json "dbconfig.json"
    require("/database", DatabaseConfig)
  }

  handlers {
    get {
      render groovyMarkupTemplate("index.gtpl", title: "My Ratpack App")
    }

    files { dir "public" }
  }
}
