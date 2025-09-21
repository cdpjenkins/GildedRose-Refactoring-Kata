package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `Item's name remains unchanged`() {
        val items = listOf(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("foo", app.items[0].name)

    }

    @Test
    fun `sellIn is decremented by 1each day`() {
        val app = GildedRose(listOf(Item("+5 Dexterity Vest", 10, 20)))

        app.updateQuality()
        assertEquals(9, app.items[0].sellIn)
    }

    @Test
    fun `quality is decremented by 1 each day when sellIn is greater than 0`() {
        val app = GildedRose(listOf(Item("+5 Dexterity Vest", 10, 20)))

        app.updateQuality()
        assertEquals(19, app.items[0].quality)
    }

    @Test
    fun `once the sell by date has passed, quality degrades twice as fast`() {
        val app = GildedRose(listOf(Item("+5 Dexterity Vest", 0, 10)))

        app.updateQuality()
        assertEquals(8, app.items[0].quality)
    }

    @Test
    fun `the quality of an item is never negative`() {
        val app = GildedRose(listOf(Item("+5 Dexterity Vest", 1, 0)))

        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun `Aged Bries actually increases in Quality the older it gets`() {
        val app = GildedRose(listOf(Item("Aged Brie", 2, 0)))
        app.updateQuality()
        assertEquals(1, app.items[0].quality)
    }

    @Test
    fun `The quality of an item is never more than 50`() {
        val app = GildedRose(listOf(Item("Aged Brie", 2, 50)))
        app.updateQuality()
        assertEquals(50, app.items[0].quality)
    }

    @Test
    fun `Sulfuras, being a legendary item, never has to be sold or decreases in quality`() {
        val app = GildedRose(listOf(Item("Sulfuras, Hand of Ragnaros", 0, 80)))
        app.updateQuality()
        assertEquals(80, app.items[0].quality)
    }

    @Test
    fun `Backstage passes, like aged brie, increases in quality as its sellIn value approaches`() {
        val app = GildedRose(listOf(Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)))
        app.updateQuality()
        assertEquals(21, app.items[0].quality)
    }

    @Test
    fun `Backstage passes quality increases by 2 when there are 10 days or less`() {
        val app = GildedRose(listOf(Item("Backstage passes to a TAFKAL80ETC concert", 10, 25)))
        app.updateQuality()
        assertEquals(27, app.items[0].quality)
    }

    @Test
    fun `Backstage passes quality increases by 3 when there are 5 days or less`() {
        val app = GildedRose(listOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 35)))
        app.updateQuality()
        assertEquals(38, app.items[0].quality)
    }

    @Test
    fun `Backstage passes quality drops to 0 after the concert`() {
        val app = GildedRose(listOf(Item("Backstage passes to a TAFKAL80ETC concert", 0, 50)))
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }
}


