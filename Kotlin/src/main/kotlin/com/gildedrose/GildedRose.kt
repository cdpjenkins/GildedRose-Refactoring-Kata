package com.gildedrose

import com.gildedrose.ItemType.SULFURAS
import com.gildedrose.ItemType.AGED_BRIE
import com.gildedrose.ItemType.BACKSTAGE_PASSES

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

        if ((type == AGED_BRIE) || (type == BACKSTAGE_PASSES)) {
            if (quality < 50) {
                quality = quality + 1

                if (type == BACKSTAGE_PASSES) {
                    if (sellIn < 11) {
                        if (quality < 50) {
                            quality = quality + 1
                        }
                    }

                    if (sellIn < 6) {
                        if (quality < 50) {
                            quality = quality + 1
                        }
                    }
                }
            }
        } else {
            if (quality > 0) {
                if (!(type == SULFURAS)) {
                    quality = quality - 1
                }
            }
        }

        sellIn = type.getUpdatedSellIn(this)

        if (type == SULFURAS) {
            // Do nothing
        } else {
            if (sellIn < 0) {
                if (type == AGED_BRIE) {
                    if (quality < 50) {
                        quality = quality + 1
                    }
                } else {
                    if (type == BACKSTAGE_PASSES) {
                        quality = 0
                    } else {
                        if (quality > 0) {
                            quality = quality - 1
                        }
                    }
                }
            }
        }
    }
}

