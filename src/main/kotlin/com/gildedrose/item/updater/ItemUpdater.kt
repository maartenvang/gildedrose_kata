package com.gildedrose.item.updater

import com.gildedrose.item.Item

interface ItemUpdater {

    /**
     * Updates the sellIn day of an item
     */
    fun updateSellIn(item: Item)

    /**
     * Updates the quality, should be called after updating the sellIn day
     */
    fun updateQuality(item: Item)

}