package com.gildedrose.item.updater

import com.gildedrose.item.Item

class BackstagePassItemUpdater : NormalItemUpdater() {

    private companion object {
        private const val TEN_DAYS = 10
        private const val FIVE_DAYS = 5
    }

    /**
     * Backstage passes increase in quality:
     * - Quality increases by 1 every day if the sellIn day is >10 days
     * - Quality increases by 2 if the sellIn day is 10 or less
     * - Quality increases by 3 if the sellIn day is 5 or less
     * If the sellIn date has been reached, a backstage pass loses its quality
     */
    override fun updateQuality(item: Item) {
        increaseQuality(item)
        if (item.sellIn < TEN_DAYS) {
            increaseQuality(item)
        }
        if (item.sellIn < FIVE_DAYS) {
            increaseQuality(item)
        }
        if (item.sellIn < 0) {
            item.quality = 0
        }
    }

}