package browserTest

import browserTest.pages.AddHospitalPage
import geb.spock.GebSpec
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
/**
 * Created by pocockn on 20/04/16.
 */
class AddGamePageTest extends GebSpec {

    def aut = new GroovyRatpackMainApplicationUnderTest()

    def setup() {
        URI base = aut.address
        browser.baseUrl = base.toString()
    }

   def "Add hospital form"() {
       given:
       to AddHospitalPage

       when: "I add a new hospital and submit data"
       addHospitalForm.with {
           name = "UCL"
           employees = "2500"
           address = "London Maple Street"
       }
       submitButton.click()

       then: "check submitted form data is correct"
           $("li.video-title").text().contains("UCL")
   }
}


