package com.gildedrose

import com.gildedrose.ItemType.SULFURAS
import com.gildedrose.ItemType.AGED_BRIE
import com.gildedrose.ItemType.BACKSTAGE_PASSES
import kotlin.math.max
import kotlin.math.min

enum class ItemType {
    SULFURAS {
        override fun getUpdatedSellIn(item: Item): Int = item.sellIn
    },
    AGED_BRIE,
    BACKSTAGE_PASSES,
    OTHER;

    open fun getUpdatedSellIn(item: Item): Int = item.sellIn - 1



    companion object {
        fun of(name: String): ItemType =
            when (name) {
                "Sulfuras, Hand of Ragnaros" -> SULFURAS
                "Aged Brie" -> AGED_BRIE
                "Backstage passes to a TAFKAL80ETC concert" -> BACKSTAGE_PASSES
                else -> OTHER
            }
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

        quality = when (type) {
            AGED_BRIE -> {
                val updatedQuality =
                    if (sellIn >= 0) quality + 1
                    else quality + 2

                min(updatedQuality, 50)
            }
            BACKSTAGE_PASSES -> {
                val updatedQuality =
                    when {
                        sellIn < 0 -> 0
                        sellIn < 5 -> quality + 3
                        sellIn < 10 -> quality + 2
                        else -> quality + 1
                    }

                min(updatedQuality, 50)
            }
            SULFURAS -> {
                quality
            }
            else -> {
                val updatedQuality =
                    if (sellIn < 0) {
                        quality - 2
                    } else {
                        quality - 1
                    }

                max(updatedQuality, 0)
            }
        }
    }
}

