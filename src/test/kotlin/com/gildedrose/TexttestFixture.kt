package com.gildedrose

fun main(args: Array<String>) {
    val items = arrayOf(Item("+5 Dexterity Vest", 10, 20), //
                        Item("Aged Brie", 2, 0), //
                        Item("Elixir of the Mongoose", 5, 7), //
                        Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                        Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                        Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                        Item("Conjured Mana Cake", 3, 6))
    val app = GildedRose(items)

    var days = 2
    if (args.isNotEmpty()) {
        days = Integer.parseInt(args[0]) + 1
    }

    for (i in 0 until days) {
        println("-------- day $i --------")
        println("name, sellIn, quality")
        for (item in items) {
            println(item)
        }
        println()
        app.updateQuality()
    }

}