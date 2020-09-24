package com.gildedrose.item.updater

import com.gildedrose.item.Item

open class NormalItemUpdater : ItemUpdater {

    override fun updateSellIn(item: Item) {
        --item.sellIn
    }

    /**
     * Normal items degrade in quality each day. After reaching the sellIn date, they degrade twice as fast.
     */
    override fun updateQuality(item: Item) {
        decreaseQuality(item)
        if (item.sellIn < 0) {
            decreaseQuality(item)
        }
    }

    /**
     * Decreases the quality of an item by one. The quality of an item cannot drop below a defined minimum (0).
     */
    protected fun decreaseQuality(item: Item) {
        if (item.quality > Item.QUALITY_MIN) {
            --item.quality
        }
    }

    /**
     * Increases the quality of an item by one. The quality of an item cannot go above a defined maximum (50).
     */
    protected fun increaseQuality(item: Item) {
        if (item.quality < Item.QUALITY_MAX) {
            ++item.quality
        }
    }

}