package model
/**
 * Created by pocockn on 08/06/16.
 */
class Product {

    String id
    String name
    Long quantity
    BigDecimal price

    BigDecimal getPricePerUnit() {
        this.price / this.quantity
    }

}
