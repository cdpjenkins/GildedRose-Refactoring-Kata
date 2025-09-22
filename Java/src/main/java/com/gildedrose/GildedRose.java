package com.gildedrose;

abstract class ItemUpdater {
    abstract void update(Item item);
}

class AgedBrieUpdater extends ItemUpdater {
    @Override
    void update(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }
        }
    }
}

class BackstagePassesUpdater extends ItemUpdater {
    @Override
    void update(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (item.sellIn < 11) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }

            if (item.sellIn < 6) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }
}

class SulfurasUpdater extends ItemUpdater {
    @Override
    void update(Item item) {
        // do nothing
    }
}

class OtherUpdater extends ItemUpdater {
    @Override
    void update(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            if (item.quality > 0) {
                item.quality = item.quality - 1;
            }
        }
    }
}

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
        }
    }

    private static void updateItemQuality(Item item) {
        boolean isAgedBrie = item.name.equals("Aged Brie");
        boolean isBackstagePasses = item.name.equals("Backstage passes to a TAFKAL80ETC concert");
        boolean isSulfuras = item.name.equals("Sulfuras, Hand of Ragnaros");

        ItemUpdater updater;
        if (isAgedBrie) {
            updater = new AgedBrieUpdater();
        } else if (isBackstagePasses) {
            updater = new BackstagePassesUpdater();
        } else if (isSulfuras) {
            updater = new SulfurasUpdater();
        } else {
            updater = new OtherUpdater();
        }

        updater.update(item);
    }

}
