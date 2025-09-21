package com.gildedrose

import kotlin.math.max
import kotlin.math.min

enum class ItemType {
    SULFURAS {
        override fun getUpdatedSellIn(item: Item): Int = item.sellIn
        override fun getUpdatedQuality(item: Item): Int {
            return item.quality
        }
    },
    AGED_BRIE {
        override fun getUpdatedQuality(item: Item): Int {
            val updatedQuality =
                if (item.sellIn >= 0) item.quality + 1
                else item.quality + 2

            return min(updatedQuality, 50)
        }
    },
    BACKSTAGE_PASSES {
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
    },
    CONJURED {
        override fun getUpdatedQuality(item: Item): Int {
            val updatedQuality =
                if (item.sellIn < 0) item.quality - 4
                else item.quality - 2

            return max(updatedQuality, 0)
        }
    },
    OTHER {
        override fun getUpdatedQuality(item: Item): Int {
            val updatedQuality =
                if (item.sellIn < 0) item.quality - 2
                else item.quality - 1

            return max(updatedQuality, 0)
        }
    };

    open fun getUpdatedSellIn(item: Item): Int = item.sellIn - 1
    abstract fun getUpdatedQuality(item: Item): Int

    companion object {
        fun of(name: String): ItemType =
            if (name.startsWith("Sulfuras")) SULFURAS
            else if (name.startsWith("Aged Brie")) AGED_BRIE
            else if (name.startsWith("Backstage passes")) BACKSTAGE_PASSES
            else if (name.startsWith("Conjured")) CONJURED
            else OTHER
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
