package com.gildedrose

import com.gildedrose.item.Item
import com.gildedrose.item.updater.ItemUpdaterFactory

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach { item ->
            ItemUpdaterFactory.itemUpdaterFor(item).apply {
                updateSellIn(item)
                updateQuality(item)
            }
        }
    }

}

