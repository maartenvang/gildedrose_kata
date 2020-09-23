package com.gildedrose.item

open class Item(var name: String,
                var sellIn: Int,
                var quality: Int) {

    companion object {
        const val QUALITY_MIN = 0
        const val QUALITY_MAX = 50
    }

    override fun toString(): String {
        return this.name + ", " + this.sellIn + ", " + this.quality
    }

}