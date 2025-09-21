package com.gildedrose

class GildedRose(val items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            if ((isAgedBrie(item)) || (isBackstagePasses(item))) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1

                    if (isBackstagePasses(item)) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1
                            }
                        }
                    }
                }
            } else {
                if (item.quality > 0) {
                    if (!isSulfuras(item)) {
                        item.quality = item.quality - 1
                    }
                }
            }

            if (!(isSulfuras(item))) {
                item.sellIn = item.sellIn - 1
            }

            if (item.sellIn < 0) {
                if (isAgedBrie(item)) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1
                    }
                } else {
                    if (isBackstagePasses(item)) {
                        item.quality = 0
                    } else {
                        if (item.quality > 0) {
                            if (!isSulfuras(item)) {
                                item.quality = item.quality - 1
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun isSulfuras(item: Item): Boolean = item.name == "Sulfuras, Hand of Ragnaros"
private fun isBackstagePasses(item: Item): Boolean = item.name == "Backstage passes to a TAFKAL80ETC concert"
private fun isAgedBrie(item: Item): Boolean = item.name == "Aged Brie"
