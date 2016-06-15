package model

import geb.spock.GebSpec

/**
 * Created by pocockn on 14/06/16.
 */
class HospitalSpec extends GebSpec {

    def "Hospital model is created"() {
        when:
        def hospital = new Hospital (id: UUID.randomUUID().toString(), name: "Tooting", employees: "2500", address: "Tooting Road")

        then:
        hospital.name == "Tooting"
        hospital.employees == "2500"
    }
}
