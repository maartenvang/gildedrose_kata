package com.gildedrose.item

open class Item(var name: String,
                var sellIn: Int,
                var quality: Int) {

    companion object {
        const val QUALITY_MIN = 0
        const val QUALITY_MAX = 50
        const val ITEM_TYPE_AGED_BRIE = "Aged Brie"
        const val ITEM_TYPE_BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
        const val ITEM_TYPE_LEGENDARY = "Sulfuras, Hand of Ragnaros"
        const val ITEM_TYPE_CONJURED = "Conjured Mana Cake"
    }

    override fun toString(): String {
        return this.name + ", " + this.sellIn + ", " + this.quality
    }

}