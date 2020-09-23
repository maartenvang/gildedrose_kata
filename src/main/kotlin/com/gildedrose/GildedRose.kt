package com.gildedrose

import com.gildedrose.item.Item
import com.gildedrose.item.updater.ItemUpdaterProvider

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach { item ->
            ItemUpdaterProvider.provideItemUpdater(item).apply {
                updateSellIn(item)
                updateQuality(item)
            }
        }
    }

}

