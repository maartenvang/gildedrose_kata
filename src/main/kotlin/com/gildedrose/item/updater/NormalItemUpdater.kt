package com.gildedrose.item.updater

import com.gildedrose.item.Item

open class NormalItemUpdater : ItemUpdater {

    override fun updateSellIn(item: Item) {
        --item.sellIn
    }

    override fun updateQuality(item: Item) {
        decreaseQuality(item)
        if (item.sellIn < 0) {
            decreaseQuality(item)
        }
    }

    protected fun decreaseQuality(item: Item) {
        if (item.quality > Item.QUALITY_MIN) {
            --item.quality
        }
    }

    protected fun increaseQuality(item: Item) {
        if (item.quality < Item.QUALITY_MAX) {
            ++item.quality
        }
    }

}