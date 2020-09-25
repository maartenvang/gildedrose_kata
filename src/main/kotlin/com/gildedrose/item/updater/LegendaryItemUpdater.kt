package com.gildedrose.item.updater

import com.gildedrose.item.Item

class LegendaryItemUpdater : ItemUpdater {

    /**
     * Legendary items do not update their sellIn value and do not degrade in quality
     */
    override fun updateItem(item: Item) {
        // No-op
    }

}