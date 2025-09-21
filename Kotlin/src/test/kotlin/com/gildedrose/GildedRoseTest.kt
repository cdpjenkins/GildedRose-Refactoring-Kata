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
}


