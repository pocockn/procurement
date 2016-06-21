package model

import geb.spock.GebSpec

/**
 * Created by pocockn on 14/06/16.
 */
class ProductSpec extends GebSpec {

    def "Product model is created"() {
        when:
        def product = new Product(id: UUID.randomUUID().toString(), name: "Chainsaw", quantity: "2500", price: "6000")

        then:
        product.name == "Chainsaw"
        product.quantity == "2500"
        product.price == "6000"
    }
}
