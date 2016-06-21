package services

import model.Hospital
import ratpack.test.exec.ExecHarness
import service.StorageServiceHospitalImplementation
import spock.lang.AutoCleanup
import spock.lang.Specification

/**
 * Created by pocockn on 16/06/16.
 */

class HospitalServiceImplementationSpec extends Specification {

    private static final def hospitals = [
            new Hospital(id: '79dfed0c-13f1-4e9c-b472-0fa857355bef', name: "Test Hospital", employees: "2500", address: "Test Address"),
            new Hospital(id: '79dfed0c-13f1-4e9c-b472-0fa857355bed', name: "Test Hospital", employees: "2500", address: "Test Address"),
            new Hospital(id: '79dfed0c-13f1-4e9c-b472-0fa857355bee', name: "Test Hospital", employees: "2500", address: "Test Address")
    ]

    @AutoCleanup
    ExecHarness execHarness = ExecHarness.harness()

    StorageServiceHospitalImplementation store = new StorageServiceHospitalImplementation()

    void "Should save and return a list of hospitals"() {
        given:
        execHarness.yield {
            hospitals.each {
                hospital -> store.save(hospital)
            }
        }

        expect:
        def hello = []
        execHarness.yieldSingle {
            store.fetchAll().then {
                hello.add(it)
            }
        }
        println hello

    }
}

