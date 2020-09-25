package com.gildedrose.item.updater

import com.gildedrose.item.Item

class ItemUpdaterProvider {

    private val defaultItemUpdater = BasicItemUpdater()
    private val specificItemUpdaters = mapOf(Item.ITEM_TYPE_AGED_BRIE to AgedBrieItemUpdater(),
                                             Item.ITEM_TYPE_BACKSTAGE_PASS to BackstagePassItemUpdater(),
                                             Item.ITEM_TYPE_LEGENDARY to LegendaryItemUpdater(),
                                             Item.ITEM_TYPE_CONJURED to ConjuredItemUpdater())

    fun itemUpdaterFor(item: Item): ItemUpdater {
        val specificItemUpdater = specificItemUpdaters[item.name]
        return specificItemUpdater ?: defaultItemUpdater
    }

}