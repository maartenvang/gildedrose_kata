package com.gildedrose.item.updater

import com.gildedrose.item.Item

class LegendaryItemUpdater : ItemUpdater {

    override fun updateSellIn(item: Item) {
        // No-op: Legendary items do not update their sellIn value
    }

    override fun updateQuality(item: Item) {
        // No-op: Legendary items do not degrade in quality
    }

}