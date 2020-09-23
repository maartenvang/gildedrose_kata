package com.gildedrose.item.updater

import com.gildedrose.item.Item

class ConjuredItemUpdater : NormalItemUpdater() {

    /**
     * Conjured items age twice as fast as normal items
     */
    override fun updateQuality(item: Item) {
        decreaseQuality(item)
        decreaseQuality(item)
    }

}