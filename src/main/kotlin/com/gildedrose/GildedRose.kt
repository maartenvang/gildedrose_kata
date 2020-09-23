package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach { item ->
            when (item.name) {
                "Aged Brie" -> updateAgedBrieQuality(item)
                "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePassQuality(item)
                "Sulfuras, Hand of Ragnaros" -> updateSulfurasQuality(item)
                "Conjured Mana Cake" -> updateConjuredQuality(item)
                else -> updateNormalItemQuality(item)
            }
        }
    }

    private fun updateAgedBrieQuality(item: Item) {
        increaseValue(item)
        decreaseSellIn(item)

        if (item.sellIn < 0) {
            increaseValue(item)
        }
    }

    private fun updateBackstagePassQuality(item: Item) {
        increaseValue(item)
        if (item.sellIn < 11) {
            increaseValue(item)
        }
        if (item.sellIn < 6) {
            increaseValue(item)
        }
        decreaseSellIn(item)
        if (item.sellIn < 0) {
            item.quality = 0
        }
    }

    private fun updateConjuredQuality(item: Item) {
        decreaseQuality(item)
        decreaseQuality(item) // Conjured items degrade in quality twice as fast as normal items
        decreaseSellIn(item)
        if (item.sellIn < 0) {
            decreaseQuality(item)
        }
    }

    private fun updateNormalItemQuality(item: Item) {
        decreaseQuality(item)
        decreaseSellIn(item)
        if (item.sellIn < 0) {
            decreaseQuality(item)
        }
    }

    private fun updateSulfurasQuality(item: Item) {
        // No-op: Sulfuras items do not degrade in quality and their sellIn date does not change.
    }

    private fun decreaseQuality(item: Item) {
        if (item.quality > 0) {
            --item.quality
        }
    }

    private fun decreaseSellIn(item: Item) {
        item.sellIn = item.sellIn - 1
    }

    private fun increaseValue(item: Item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1
        }
    }

}

