package com.gildedrose.item.updater

import com.gildedrose.item.Item

object ItemUpdaterFactory {

    fun itemUpdaterFor(item: Item): ItemUpdater {
        return when (item.name) {
            "Aged Brie" -> AgedBrieItemUpdater()
            "Backstage passes to a TAFKAL80ETC concert" -> BackstagePassItemUpdater()
            "Sulfuras, Hand of Ragnaros" -> LegendaryItemUpdater()
            "Conjured Mana Cake" -> ConjuredItemUpdater()
            else -> NormalItemUpdater()
        }
    }

}