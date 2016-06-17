package ratpack

import org.junit.After
import ratpack.exec.ExecResult
import ratpack.exec.Operation
import ratpack.exec.Promise
import ratpack.func.Factory
import ratpack.test.exec.ExecHarness

import static ratpack.test.exec.ExecHarness.harness

/**
 * Created by pocockn on 16/06/16.
 */
trait AsyncSpec {

    ExecHarness execHarness = harness()

    @After
    void shutdownHarness() {
        execHarness.close()
    }

    public <T> ExecResult<T> yield(Factory<Promise<T>> promiseFactory) {
        execHarness.yield { promiseFactory.create() }
    }

    public <T> T yieldResult(Factory<Promise<T>> promiseFactory) {
        yield(promiseFactory).valueOrThrow
    }

    public void execute(Factory<Operation> operationFactory) {
        execHarness.execute { operationFactory.create() }
    }

    public static <T> Promise<T> promise(T val) { Promise.value(val)}

    public static Promise errorPromise(String message) { Promise.error(new RuntimeException(message))}

    public static Operation errorOperation(String message) {
        Promise.error(new RuntimeException(message)).operation()
    }

}
