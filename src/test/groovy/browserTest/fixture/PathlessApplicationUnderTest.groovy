package browserTest.fixture

/**
 * Created by bartolottia on 27/04/2016.
 */
import ratpack.test.ApplicationUnderTest

class PathlessApplicationUnderTest implements ApplicationUnderTest {

    private final ApplicationUnderTest delegate

    PathlessApplicationUnderTest(ApplicationUnderTest delegate) {
        this.delegate = delegate
    }

    URI getAddress() {
        def address = delegate.address
        new URI(address.scheme, null, address.host, address.port, "/", null, null)
    }

}
