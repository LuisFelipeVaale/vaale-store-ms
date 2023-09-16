package co.vaale.store.getstores.infrastructure.jooq

import org.jooq.Table
import org.jooq.impl.DSL


object StoreTable {
    val ID = DSL.field("commerce_place.commerce_id", Long::class.java)
    val NAME = DSL.field("commerce_place.name", String::class.java)
    val CATEGORY = DSL.field("commerce_place.commerce_classification_id", Int::class.java)
    val CITY = DSL.field("commerce_place.city_id", Int::class.java)
    val IMAGE = DSL.field("commerce_place.img_url", String::class.java)
    val PHONE = DSL.field("commerce_place.cell_phone", String::class.java)
    val EMAIL = DSL.field("commerce_place.email", String::class.java)
    val ADDRESS = DSL.field("commerce_place.address", String::class.java)
    val LAT = DSL.field("commerce_place.lat", Double::class.java)
    val LON = DSL.field("commerce_place.lon", Double::class.java)
    val STORES: Table<*> = DSL.table("commerce_place")

}