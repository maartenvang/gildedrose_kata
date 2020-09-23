package com.gildedrose.item.updater

import com.gildedrose.item.Item

open class NormalItemUpdater : ItemUpdater {

    override fun updateSellIn(item: Item) {
        decreaseSellIn(item)
    }

    override fun updateQuality(item: Item) {
        decreaseQuality(item)
        if (item.sellIn < 0) {
            decreaseQuality(item)
        }
    }

    protected fun decreaseQuality(item: Item) {
        if (item.quality > 0) {
            --item.quality
        }
    }

    protected fun decreaseSellIn(item: Item) {
        --item.sellIn
    }

    protected fun increaseQuality(item: Item) {
        if (item.quality < 50) {
            item.quality++
        }
    }

}