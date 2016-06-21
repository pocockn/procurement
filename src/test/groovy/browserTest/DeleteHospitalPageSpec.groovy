package browserTest

import browserTest.pages.AddHospitalPage
import browserTest.pages.HospitalPage
import browserTest.pages.HospitalsPage
import geb.spock.GebSpec
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.ApplicationUnderTest

/**
 * Created by pocockn on 20/04/16.
 */
class DeleteHospitalPageSpec extends GebSpec {

    ApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest()

    def setup() {
        browser.baseUrl = aut.address.toString()
    }

    def "Add a hospital"() {
       when:
       to AddHospitalPage

        then: "I add a new hospital and submit data"
       addHospitalForm.with {
           name = "UCL"
           employees = "2500"
           address = "London Maple Street"
       }
       submitButton.click()

        to HospitalsPage

        then: "click on individual hospital"
        singleHospitalLink.click()

        and: "go to our list of hospitals"
        at HospitalPage
        deleteButton.click()

        expect:
        at HospitalsPage
        !singleHospitalLink.present()
   }

}


