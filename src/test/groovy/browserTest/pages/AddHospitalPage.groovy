package browserTest.pages
import geb.Page

/**
 * Created by pocockn on 20/04/16.
 */
class AddHospitalPage extends Page {
    // location of the page
    static url = "api/addHospital"
    // at is used to assert driver is on the correct page, checks title to ensure this
    static at = { title == "Add New Hospital" }

    static content = {
        addHospitalForm { $("form") }
        submitButton { (addHospitalForm.submit()) }
    }

}