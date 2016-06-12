package browserTest

import geb.spock.GebSpec
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import spock.lang.AutoCleanup
import spock.lang.Ignore

/**
 * Created by bartolottia on 27/04/2016.
 */
class ListGamesTest extends GebSpec {

    @AutoCleanup
    def aut = new GroovyRatpackMainApplicationUnderTest()

    //Remote control allows direct access to the AUT's registry
    //RemoteControl remoteControl = new RemoteControl(new PathlessApplicationUnderTest(aut))


    def setup() {
        URI base = aut.address
        browser.baseUrl = base.toString()

//        exec {
//            def harness = new DefaultExecHarness(get(ExecController))
//            command.delegate = delegate
//            command.resolveStrategy = Closure.DELEGATE_ONLY
//            harness.yield {
//                command.call()
//            }.valueOrThrow
//        }
//
//        remoteControl.exec {
//            get(DefaultVideoGameService).save(new VideoGame(UUID.randomUUID(),"Test Game",67,"I am the test game"))
//        }
    }

    @Ignore
    def "Check all games list"() {

        given:
        to GamesPage

        expect:
        gameListContainer


    }

}
