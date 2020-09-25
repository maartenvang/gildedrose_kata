package com.gildedrose.item.updater

import com.gildedrose.item.Item

class ConjuredItemUpdater : BasicItemUpdater() {

    /**
     * Conjured items age twice as fast as normal items
     */
    override fun updateQuality(item: Item) {
        decreaseQuality(item)
        decreaseQuality(item)
    }

}