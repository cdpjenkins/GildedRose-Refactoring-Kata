package com.gildedrose;

abstract class ItemUpdater {
    abstract void update(Item item);
}

class AgedBrieUpdater extends ItemUpdater {
    @Override
    void update(Item item) {
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality += 2;
        } else {
            item.quality += 1;
        }

        item.quality = Math.min(50, item.quality);
    }
}

class BackstagePassesUpdater extends ItemUpdater {
    @Override
    void update(Item item) {
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = 0;
        } else if (item.sellIn < 5) {
            item.quality = item.quality + 3;
        } else if (item.sellIn < 10) {
            item.quality = item.quality + 2;
        } else  {
            item.quality = item.quality + 1;
        }

        item.quality = Math.min(50, item.quality);
    }
}

class SulfurasUpdater extends ItemUpdater {
    @Override
    void update(Item item) {
        // do nothing
    }
}

class ConjuredUpdater extends ItemUpdater {
    @Override
    void update(Item item) {
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = item.quality - 4;
        } else {
            item.quality = item.quality - 2;
        }

        item.quality = Math.max(0, item.quality);

    }
}

class OtherUpdater extends ItemUpdater {
    @Override
    void update(Item item) {
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = item.quality - 2;
        } else {
            item.quality = item.quality - 1;
        }

        item.quality = Math.max(0, item.quality);
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
        } else if (item.name.equals("Conjured Mana Cake")) {
            updater = new ConjuredUpdater();
        } else {
            updater = new OtherUpdater();
        }

        updater.update(item);
    }
}
