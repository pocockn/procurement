/**
 * Created by pocockn on 13/06/16.
 */

import org.openqa.selenium.firefox.FirefoxDriver

baseUrl = "http://localhost:5050"

driver = { new FirefoxDriver() }

reportsDir = new File("target/geb-reports")
reportOnTestFailureOnly = true