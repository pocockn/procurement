package services

import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.http.client.ReceivedResponse
import ratpack.test.http.TestHttpClient
import spock.lang.AutoCleanup
import spock.lang.Specification

import static ratpack.http.MediaType.APPLICATION_FORM

/**
 * Created by pocockn on 14/06/16.
 */
class HospitalImplementationServiceUnitTest extends Specification {

    @AutoCleanup
    GroovyRatpackMainApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest()

    @Delegate
    TestHttpClient testClient = aut.httpClient

    void "Bootstrap some data and render it back"() {
        when: "I create the request for my form"
        testClient.requestSpec {
            it.headers.add("Content-Type", APPLICATION_FORM)
            it.body.stream({
                it << [name: "Tooting", employees: "2500", address: "TootingRoad"].collect ({
                    it
                }).join('&')
            })
        }

        then:"send the request we built to our server via post"
        ReceivedResponse receivedResponse = testClient.post('/api/hospitals')
        receivedResponse.statusCode == 200

        and:"our home page should contain our new hospital"
        testClient.get('/').body.text.contains("TootingRoad")


    }
}
