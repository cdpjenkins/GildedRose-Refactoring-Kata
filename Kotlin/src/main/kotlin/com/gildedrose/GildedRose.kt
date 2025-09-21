package com.gildedrose

import com.gildedrose.ItemType.SULFURAS
import com.gildedrose.ItemType.AGED_BRIE
import com.gildedrose.ItemType.BACKSTAGE_PASSES

enum class ItemType {
    SULFURAS,
    AGED_BRIE,
    BACKSTAGE_PASSES,
    OTHER;

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

                if (ItemType.of(name) == BACKSTAGE_PASSES) {
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
                if (!(ItemType.of(name) == SULFURAS)) {
                    quality = quality - 1
                }
            }
        }

        if (!(ItemType.of(name) == SULFURAS)) {
            sellIn = sellIn - 1
        }

        if (sellIn < 0) {
            if (ItemType.of(name) == AGED_BRIE) {
                if (quality < 50) {
                    quality = quality + 1
                }
            } else {
                if (ItemType.of(name) == BACKSTAGE_PASSES) {
                    quality = 0
                } else {
                    if (quality > 0) {
                        if (!(ItemType.of(name) == SULFURAS)) {
                            quality = quality - 1
                        }
                    }
                }
            }
        }
    }
}
