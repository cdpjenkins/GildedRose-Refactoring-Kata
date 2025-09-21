package com.gildedrose

class GildedRose(val items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            item.updateQuality()
        }
    }

    private fun Item.updateQuality() {
        if ((isAgedBrie(this)) || (isBackstagePasses(this))) {
            if (quality < 50) {
                quality = quality + 1

                if (isBackstagePasses(this)) {
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
                if (!isSulfuras(this)) {
                    quality = quality - 1
                }
            }
        }

        if (!(isSulfuras(this))) {
            sellIn = sellIn - 1
        }

        if (sellIn < 0) {
            if (isAgedBrie(this)) {
                if (quality < 50) {
                    quality = quality + 1
                }
            } else {
                if (isBackstagePasses(this)) {
                    quality = 0
                } else {
                    if (quality > 0) {
                        if (!isSulfuras(this)) {
                            quality = quality - 1
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
