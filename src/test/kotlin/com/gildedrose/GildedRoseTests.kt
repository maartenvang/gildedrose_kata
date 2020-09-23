package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTests {

    @Test
    fun `Once the sell by date has passed, Quality degrades twice as fast`() {
        val items = arrayOf(Item("quality 100 item", 2, 100))
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality() // First run: quality should degrade and sellIn should go down by one
        assertEquals(1, gildedRose.items.first().sellIn)
        assertEquals(99, gildedRose.items.first().quality)

        gildedRose.updateQuality() // Second run: quality should degrade and sellIn should go down by one
        assertEquals(0, gildedRose.items.first().sellIn)
        assertEquals(98, gildedRose.items.first().quality)

        gildedRose.updateQuality() // Third run: sellIn should go down by one, quality should degrade by two since the sellIn has reached < 0
        assertEquals(-1, gildedRose.items.first().sellIn)
        assertEquals(96, gildedRose.items.first().quality)
    }

    @Test
    fun `The Quality of an item is never negative`() {
        val items = arrayOf(Item("quality 1 item", 2, 1))
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality() // First run: quality should degrade and sellIn should go down by one
        assertEquals(1, gildedRose.items.first().sellIn)
        assertEquals(0, gildedRose.items.first().quality)

        gildedRose.updateQuality() // Second run: quality should not drop below zero
        assertEquals(0, gildedRose.items.first().sellIn)
        assertEquals(0, gildedRose.items.first().quality)

        gildedRose.updateQuality() // Third run: quality should not drop below zero, also when sell by date has passed
        assertEquals(-1, gildedRose.items.first().sellIn)
        assertEquals(0, gildedRose.items.first().quality)
    }

    @Test
    fun `"Aged Brie" actually increases in Quality the older it gets`() {
        val items = arrayOf(Item("Aged Brie", 2, 1))
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality() // First run: quality should go up for Aged Brie and sellIn should go down by one
        assertEquals(1, gildedRose.items.first().sellIn)
        assertEquals(2, gildedRose.items.first().quality)

        gildedRose.updateQuality() // Second run: quality should go up for Aged Brie and sellIn should go down by one
        assertEquals(0, gildedRose.items.first().sellIn)
        assertEquals(3, gildedRose.items.first().quality)
    }

    @Test
    fun `The Quality of an item is never more than 50`() {
        val items = arrayOf(Item("Aged Brie", 2, 49))
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality() // First run: quality should go up for Aged Brie and sellIn should go down by one
        assertEquals(1, gildedRose.items.first().sellIn)
        assertEquals(50, gildedRose.items.first().quality)

        gildedRose.updateQuality() // Second run: quality cannot go beyond 50
        assertEquals(0, gildedRose.items.first().sellIn)
        assertEquals(50, gildedRose.items.first().quality)
    }

    @Test
    fun `"Sulfuras", being a legendary item, never has to be sold or decreases in Quality`() {
        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 100, 100))
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality() // Update should not trigger any change for sulfuras
        assertEquals(100, gildedRose.items.first().sellIn)
        assertEquals(100, gildedRose.items.first().quality)

        gildedRose.updateQuality() // Second try just to be sure
        assertEquals(100, gildedRose.items.first().sellIn)
        assertEquals(100, gildedRose.items.first().quality)
    }

    @Test
    fun `"Backstage passes", like aged brie, increases in Quality as its SellIn value approaches`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 7, 20))
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality() // Quality increases by 2 when there are 10 days or less
        assertEquals(6, gildedRose.items.first().sellIn)
        assertEquals(22, gildedRose.items.first().quality)

        gildedRose.updateQuality() // Quality increases by 2 when there are 10 days or less
        assertEquals(5, gildedRose.items.first().sellIn)
        assertEquals(24, gildedRose.items.first().quality)

        gildedRose.updateQuality() // Quality increases by 3 when there are 5 days or less
        assertEquals(4, gildedRose.items.first().sellIn)
        assertEquals(27, gildedRose.items.first().quality)

        gildedRose.updateQuality()
        gildedRose.updateQuality()
        gildedRose.updateQuality()
        gildedRose.updateQuality() // Same as before: quality += 3 for <5 days
        assertEquals(0, gildedRose.items.first().sellIn)
        assertEquals(39, gildedRose.items.first().quality)

        gildedRose.updateQuality() // Sell by date reached: quality should drop to zero
        assertEquals(-1, gildedRose.items.first().sellIn)
        assertEquals(0, gildedRose.items.first().quality)

        gildedRose.updateQuality() // Again, sell by date reached: quality should drop to zero
        assertEquals(-2, gildedRose.items.first().sellIn)
        assertEquals(0, gildedRose.items.first().quality)
    }

    @Test
    fun `"Conjured" items degrade in Quality twice as fast as normal items`() {
        val items = arrayOf(Item("Conjured Mana Cake", 5, 3))
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality() // Quality should go down by 2 instead of 1
        assertEquals(4, gildedRose.items.first().sellIn)
        assertEquals(1, gildedRose.items.first().quality)

        gildedRose.updateQuality() // Quality should go down by 2 instead of 1, but not dip below zero
        assertEquals(3, gildedRose.items.first().sellIn)
        assertEquals(0, gildedRose.items.first().quality)
    }

    @Test
    fun `Make sure that the entire TestFixture output is correct cfr the docs`() {
        val items = arrayOf(Item("+5 Dexterity Vest", 10, 20), //
                            Item("Aged Brie", 2, 0), //
                            Item("Elixir of the Mongoose", 5, 7), //
                            Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                            Item("Sulfuras, Hand of Ragnaros", -1, 80),
                            Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                            Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                            Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                            Item("Conjured Mana Cake", 3, 6))
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality()
        assertEquals(listOf("+5 Dexterity Vest, 9, 19",
                            "Aged Brie, 1, 1",
                            "Elixir of the Mongoose, 4, 6",
                            "Sulfuras, Hand of Ragnaros, 0, 80",
                            "Sulfuras, Hand of Ragnaros, -1, 80",
                            "Backstage passes to a TAFKAL80ETC concert, 14, 21",
                            "Backstage passes to a TAFKAL80ETC concert, 9, 50",
                            "Backstage passes to a TAFKAL80ETC concert, 4, 50",
                            "Conjured Mana Cake, 2, 4"), items.map { it.toString() })

        gildedRose.updateQuality()
        assertEquals(listOf("+5 Dexterity Vest, 8, 18",
                            "Aged Brie, 0, 2",
                            "Elixir of the Mongoose, 3, 5",
                            "Sulfuras, Hand of Ragnaros, 0, 80",
                            "Sulfuras, Hand of Ragnaros, -1, 80",
                            "Backstage passes to a TAFKAL80ETC concert, 13, 22",
                            "Backstage passes to a TAFKAL80ETC concert, 8, 50",
                            "Backstage passes to a TAFKAL80ETC concert, 3, 50",
                            "Conjured Mana Cake, 1, 2"), items.map { it.toString() })
    }

}


