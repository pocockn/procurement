/**
 * Created by pocockn on 13/06/16.
 */


import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver

waiting {
    timeout = 2
}

environments {

    // run via “./gradlew chromeTest”
    // See: http://code.google.com/p/selenium/wiki/ChromeDriver
    chrome {
            System.setProperty('webdriver.chrome.driver', '/home/pocockn/dev/procurement')
            driver = {    new ChromeDriver() }
        }

    phantomJs {
        driver = { new PhantomJSDriver() }
    }

}

// To run the tests with all browsers just run “./gradlew test”

baseUrl = "http://gebish.org"