package browserTest

import browserTest.pages.AddHospitalPage
import browserTest.pages.HospitalsPage
//import database.DBCleanUp
import geb.spock.GebSpec
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.ApplicationUnderTest
import spock.lang.Shared

/**
 * Created by pocockn on 20/04/16.
 */
class AddHospitalPageTest extends GebSpec {

    ApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest()

//    @Shared
//    DBCleanUp dbCleanUp = new DBCleanUp()

    def setup() {
        browser.baseUrl = aut.address.toString()

    }

    def "Add a hospital"() {
        when:
        to AddHospitalPage

        and: "I add a new hospital and submit data"
        addHospitalForm.with {
            name = "UCL"
            employees = "2500"
            address = "London Maple Street"
        }
        submitButton.click()

        then: "go to our list of hospitals"
        to HospitalsPage

        expect:
        $("tbody").text().contains("UCL2500London Maple Street")
    }

//    def cleanupSpec() {
//        dbCleanUp.truncateDB()
//    }

}


