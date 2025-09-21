package com.gildedrose

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `Item's name remains unchanged`() {
        Item("foo", 0, 0).updateQuality().name shouldBe "foo"
    }

    @Test
    fun `sellIn is decremented by 1 each day`() {
        Item("+5 Dexterity Vest", 10, 20).updateQuality().sellIn shouldBe 9
    }

    @Test
    fun `quality is decremented by 1 each day when sellIn is greater than 0`() {
        Item("+5 Dexterity Vest", 10, 20).updateQuality().quality shouldBe 19
    }

    @Test
    fun `once the sell by date has passed, quality degrades twice as fast`() {
        Item("+5 Dexterity Vest", 0, 10).updateQuality().quality shouldBe 8
    }

    @Test
    fun `the quality of an item is never negative`() {
        Item("+5 Dexterity Vest", 1, 0).updateQuality().quality shouldBe 0
    }

    @Test
    fun `Aged Bries actually increases in Quality the older it gets`() {
        Item("Aged Brie", 2, 0).updateQuality().quality shouldBe 1
    }

    @Test
    fun `The quality of an item is never more than 50`() {
        Item("Aged Brie", 2, 50).updateQuality().quality shouldBe 50
    }

    @Test
    fun `Sulfuras, being a legendary item, never has to be sold or decreases in quality`() {
        Item("Sulfuras, Hand of Ragnaros", 0, 80).updateQuality().quality shouldBe 80
    }

    @Test
    fun `Backstage passes, like aged brie, increases in quality as its sellIn value approaches`() {
        Item("Backstage passes to a TAFKAL80ETC concert", 15, 20).updateQuality().quality shouldBe 21
    }

    @Test
    fun `Backstage passes quality increases by 2 when there are 10 days or less`() {
        Item("Backstage passes to a TAFKAL80ETC concert", 10, 25).updateQuality().quality shouldBe 27
    }

    @Test
    fun `Backstage passes quality increases by 3 when there are 5 days or less`() {
        Item("Backstage passes to a TAFKAL80ETC concert", 5, 35).updateQuality().quality shouldBe 38
    }

    @Test
    fun `Backstage passes quality drops to 0 after the concert`() {
        Item("Backstage passes to a TAFKAL80ETC concert", 0, 50).updateQuality().quality shouldBe 0
    }

    private fun Item.updateQuality() = GildedRose(listOf(this)).also { it.updateQuality() }.items[0]
}
