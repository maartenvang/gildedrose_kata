package com.gildedrose.item.updater

import com.gildedrose.item.Item

class AgedBrieItemUpdater : BasicItemUpdater() {

    /**
     * Aged brie increases in quality as it ages, doubly so after passing the sell by date
     */
    override fun updateQuality(item: Item) {
        increaseQuality(item)

        if (item.sellIn < 0) {
            increaseQuality(item)
        }
    }

}