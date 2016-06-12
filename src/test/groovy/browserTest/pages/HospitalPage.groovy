package browserTest.pages
import geb.Page

/**
 * Created by pocockn on 20/04/16.
 */
class HospitalPage extends Page {
    // location of the page
    static url = "/"
    // at is used to assert driver is on the correct page, checks title to ensure this
    static at = { title == "All Hospitals" }

    static content = {
        gameListContainer { $(".container ul") }
    }

}