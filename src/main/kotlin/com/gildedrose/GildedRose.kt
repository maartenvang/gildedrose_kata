package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach { item ->
            if (item.name == "Aged Brie" || item.name == "Backstage passes to a TAFKAL80ETC concert") {
                increaseValue(item)
                if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                    if (item.sellIn < 11) {
                        increaseValue(item)
                    }
                    if (item.sellIn < 6) {
                        increaseValue(item)
                    }
                }
            } else {
                if (item.name != "Sulfuras, Hand of Ragnaros") {
                    decreaseQuality(item)
                }
            }

            if (item.name != "Sulfuras, Hand of Ragnaros") {
                decreaseSellIn(item)
            }

            if (item.sellIn < 0) {
                if (item.name == "Aged Brie") {
                    increaseValue(item)
                } else {
                    if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                        item.quality = item.quality - item.quality
                    } else {
                        if (item.name != "Sulfuras, Hand of Ragnaros") {
                            decreaseQuality(item)
                        }
                    }
                }
            }
        }
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

