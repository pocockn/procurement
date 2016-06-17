package services

import com.google.inject.Inject
import model.Hospital
import org.shamdata.Sham
import ratpack.AsyncSpec
import ratpack.PersistenceSpec
import service.HospitalServiceImplementation
import spock.lang.Ignore
import spock.lang.Shared

/**
 * Created by pocockn on 16/06/16.
 */
@Ignore
class HospitalServiceImplementationSpec extends PersistenceSpec implements AsyncSpec {

    String id = UUID.randomUUID().toString()

    @Inject
    HospitalServiceImplementation store

    def "store and fetch hospital"() {
        given:
        Hospital hospital = new Hospital(id: id, name: "Test Hospital", employees: "2500", address: "Test Address")

        when:
        yield { store.save(hospital) }
        Hospital draft = yieldResult { store.fetchAll() }

        then:
        draft.id == hospital.id
        draft.name == hospital.name
        draft.employees == hospital.employees
    }
}
