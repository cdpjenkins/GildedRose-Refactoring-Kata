package com.gildedrose

import kotlin.math.max
import kotlin.math.min

abstract class ItemType {
    open fun getUpdatedSellIn(item: Item): Int = item.sellIn - 1
    abstract fun getUpdatedQuality(item: Item): Int

    object Sulfuras : ItemType() {
        override fun getUpdatedSellIn(item: Item) = item.sellIn
        override fun getUpdatedQuality(item: Item) = item.quality
    }

    object AgedBrie : ItemType() {
        override fun getUpdatedQuality(item: Item): Int {
            val updatedQuality =
                if (item.sellIn >= 0) item.quality + 1
                else item.quality + 2

            return min(updatedQuality, 50)
        }
    }

    object BackstagePasses : ItemType() {
        override fun getUpdatedQuality(item: Item): Int {
            val updatedQuality =
                when {
                    item.sellIn < 0 -> 0
                    item.sellIn < 5 -> item.quality + 3
                    item.sellIn < 10 -> item.quality + 2
                    else -> item.quality + 1
                }

            return min(updatedQuality, 50)
        }
    }

    object Conjured : ItemType() {
        override fun getUpdatedQuality(item: Item): Int {
            val updatedQuality =
                if (item.sellIn < 0) item.quality - 4
                else item.quality - 2

            return max(updatedQuality, 0)
        }
    }

    object Other : ItemType() {
        override fun getUpdatedQuality(item: Item): Int {
            val updatedQuality =
                if (item.sellIn < 0) item.quality - 2
                else item.quality - 1

            return max(updatedQuality, 0)
        }
    }

    companion object {
        fun of(name: String): ItemType =
            if (name.startsWith("Sulfuras")) Sulfuras
            else if (name.startsWith("Aged Brie")) AgedBrie
            else if (name.startsWith("Backstage passes")) BackstagePasses
            else if (name.startsWith("Conjured")) Conjured
            else Other
    }
}

class GildedRose(val items: List<Item>) {
    fun updateQuality() {
        for (item in items) {
            item.updateQuality()
        }
    }

    private fun Item.updateQuality() {
        val type = ItemType.of(name)

        sellIn = type.getUpdatedSellIn(this)
        quality = type.getUpdatedQuality(this)
    }
}
