package browserTest.pages
import geb.Page

/**
 * Created by pocockn on 20/04/16.
 */
class HospitalPage extends Page {

//    String convertToPath(String uri) {
//        "/hospital/$uri"
//    }
    // location of the page
    static url = "/hospital"
    // at is used to assert driver is on the correct page, checks title to ensure this
    static at = { $('.page-hospital') }

    static content = {
        hospitalName { $(".hospital-name").text() }
        hospitalEmployees { $(".hospital-employees").text() }
        hospitalAddress { $(".hospital-address").text() }
        deleteHospitalForm { $("form.deleteHospital") }
        deleteButton { (deleteHospitalForm.delete()) }

        addProductForm { $("form.addNewProduct") }
        addProductButton { $(addProductForm.add()) }
    }

}