package services

import browserTest.pages.HospitalsPage
import geb.spock.GebSpec
import groovy.sql.Sql
import model.Hospital
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.groovy.test.embed.GroovyEmbeddedApp
import ratpack.test.embed.EmbeddedApp
import ratpack.test.remote.RemoteControl
import spock.lang.Ignore
import spock.lang.Shared
import static ratpack.jackson.Jackson.json;

/**
 * Created by pocockn on 15/06/16.
 */
@Ignore
class HospitalDeleteSpec extends GebSpec {

    String id = UUID.randomUUID().toString()
    Hospital hospital = new Hospital(id: id, name: "Test Hospital", employees: "2500", address: "Test Address")

    @Shared
    GroovyRatpackMainApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest()

    @Shared
    EmbeddedApp hospitalApp = GroovyEmbeddedApp.of {
        handlers {
            all {
                render(json(hospital))
            }
        }
    }

    def setup() {
        browser.baseUrl = aut.address.toString()
    }

    def cleanupSpec() {
        RemoteControl remote = new RemoteControl(aut)
        remote.exec {
            get(Sql).execute("delete from hospitals")
        }
    }

    def "no books are listed"() {
        when:
        to HospitalsPage

        then:
        println "hello"
    }





}
