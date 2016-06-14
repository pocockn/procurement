package browserTest

import browserTest.pages.AddHospitalPage
import geb.spock.GebReportingSpec
import geb.spock.GebSpec
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.ApplicationUnderTest
import spock.lang.AutoCleanup
import spock.lang.Shared

/**
 * Created by pocockn on 20/04/16.
 */
class AddGamePageTest extends GebSpec {

    ApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest()

    def setup() {
        browser.baseUrl = aut.address.toString()
    }

   def "Add hospital form"() {
       when:
       to AddHospitalPage

       and: "I add a new hospital and submit data"
       addHospitalForm.with {
           name = "UCL"
           employees = "2500"
           address = "London Maple Street"
       }
       submitButton.click()

       then: "check submitted form data is correct"
           $(".container h1").text().contains("Thank you for adding that Hospital")
   }
}


