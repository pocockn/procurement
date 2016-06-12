package model

/**
 * Created by pocockn on 11/06/16.
 */
class HospitalSummary {

    UUID id
    String name
    String employees
    String address

    HospitalSummary(Hospital hospital) {
        this.id = hospital.id
        this.name = hospital.name
        this.employees = hospital.employees
        this.address = hospital.address
    }

}
