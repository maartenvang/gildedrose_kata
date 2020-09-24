package com.gildedrose

import com.gildedrose.item.Item
import com.gildedrose.item.updater.ItemUpdaterProvider

class GildedRose(var items: Array<Item>) {

    private val itemUpdaterProvider = ItemUpdaterProvider()

    fun updateQuality() {
        items.forEach { item ->
            itemUpdaterProvider.itemUpdaterFor(item).apply {
                updateSellIn(item)
                updateQuality(item)
            }
        }
    }

}