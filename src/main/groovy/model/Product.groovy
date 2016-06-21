package model
/**
 * Created by pocockn on 08/06/16.
 */
class Product {

    String id
    String name
    Integer quantity
    Integer price

    Integer getPricePerUnit(quantity, price) {
        price / quantity
    }

}
