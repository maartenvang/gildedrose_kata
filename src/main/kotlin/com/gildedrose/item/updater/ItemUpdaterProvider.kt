package com.gildedrose.item.updater

import com.gildedrose.item.Item

object ItemUpdaterProvider {

    private val agedBrieItemUpdater = AgedBrieItemUpdater()
    private val backstagePassItemUpdater = BackstagePassItemUpdater()
    private val legendaryItemUpdater = LegendaryItemUpdater()
    private val conjuredItemUpdater = ConjuredItemUpdater()
    private val normalItemUpdater = NormalItemUpdater()

    fun provideItemUpdater(item: Item): ItemUpdater {
        return when (item.name) {
            "Aged Brie" -> agedBrieItemUpdater
            "Backstage passes to a TAFKAL80ETC concert" -> backstagePassItemUpdater
            "Sulfuras, Hand of Ragnaros" -> legendaryItemUpdater
            "Conjured Mana Cake" -> conjuredItemUpdater
            else -> normalItemUpdater
        }
    }

}