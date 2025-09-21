package com.gildedrose

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `Item's name remains unchanged`() {
        val items = listOf(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        app.items[0].name shouldBe "foo"
    }

    @Test
    fun `sellIn is decremented by 1 each day`() {
        val app = GildedRose(listOf(Item("+5 Dexterity Vest", 10, 20)))

        app.updateQuality()
        app.items[0].sellIn shouldBe 9
    }

    @Test
    fun `quality is decremented by 1 each day when sellIn is greater than 0`() {
        val app = GildedRose(listOf(Item("+5 Dexterity Vest", 10, 20)))

        app.updateQuality()
        app.items[0].quality shouldBe 19
    }

    @Test
    fun `once the sell by date has passed, quality degrades twice as fast`() {
        val app = GildedRose(listOf(Item("+5 Dexterity Vest", 0, 10)))

        app.updateQuality()
        app.items[0].quality shouldBe 8
    }

    @Test
    fun `the quality of an item is never negative`() {
        val app = GildedRose(listOf(Item("+5 Dexterity Vest", 1, 0)))

        app.updateQuality()
        app.items[0].quality shouldBe 0
    }

    @Test
    fun `Aged Bries actually increases in Quality the older it gets`() {
        val app = GildedRose(listOf(Item("Aged Brie", 2, 0)))
        app.updateQuality()
        app.items[0].quality shouldBe 1
    }

    @Test
    fun `The quality of an item is never more than 50`() {
        val app = GildedRose(listOf(Item("Aged Brie", 2, 50)))
        app.updateQuality()
        app.items[0].quality shouldBe 50
    }

    @Test
    fun `Sulfuras, being a legendary item, never has to be sold or decreases in quality`() {
        val app = GildedRose(listOf(Item("Sulfuras, Hand of Ragnaros", 0, 80)))
        app.updateQuality()
        app.items[0].quality shouldBe 80
    }

    @Test
    fun `Backstage passes, like aged brie, increases in quality as its sellIn value approaches`() {
        val app = GildedRose(listOf(Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)))
        app.updateQuality()
        app.items[0].quality shouldBe 21
    }

    @Test
    fun `Backstage passes quality increases by 2 when there are 10 days or less`() {
        val app = GildedRose(listOf(Item("Backstage passes to a TAFKAL80ETC concert", 10, 25)))
        app.updateQuality()
        app.items[0].quality shouldBe 27
    }

    @Test
    fun `Backstage passes quality increases by 3 when there are 5 days or less`() {
        val app = GildedRose(listOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 35)))
        app.updateQuality()
        app.items[0].quality shouldBe 38
    }

    @Test
    fun `Backstage passes quality drops to 0 after the concert`() {
        val app = GildedRose(listOf(Item("Backstage passes to a TAFKAL80ETC concert", 0, 50)))
        app.updateQuality()
        app.items[0].quality shouldBe 0
    }

    private fun updateQuality(item: Item): Item {
        val app = GildedRose(listOf(item))
        app.updateQuality()


        return app.items[0]
    }
}


