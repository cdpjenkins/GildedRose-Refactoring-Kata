package com.gildedrose;

// I've got bored of making further changes to Gilded Rose at this point. It's definitely tidier than what we started
// with. And I made massive used of Emily Bache's trick for lifting conditions out of big nested ifs. Will definitely
// remember that one.

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
        UpdateStrategy.forItem(item).update(item);
    }
}

abstract class UpdateStrategy {
    abstract void update(Item item);

    static UpdateStrategy forItem(Item item) {
        boolean isAgedBrie = item.name.equals("Aged Brie");
        boolean isBackstagePasses = item.name.equals("Backstage passes to a TAFKAL80ETC concert");
        boolean isSulfuras = item.name.equals("Sulfuras, Hand of Ragnaros");

        UpdateStrategy updater;
        if (isAgedBrie) {
            updater = new AgedBrieUpdateStrategy();
        } else if (isBackstagePasses) {
            updater = new BackstagePassesUpdateStrategy();
        } else if (isSulfuras) {
            updater = new SulfurasUpdateStrategy();
        } else if (item.name.equals("Conjured Mana Cake")) {
            updater = new ConjuredUpdateStrategy();
        } else {
            updater = new DefaultUpdateStrategy();
        }
        return updater;
    }
}

class AgedBrieUpdateStrategy extends UpdateStrategy {
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

class BackstagePassesUpdateStrategy extends UpdateStrategy {
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

class SulfurasUpdateStrategy extends UpdateStrategy {
    @Override
    void update(Item item) {
        // do nothing
    }
}

class ConjuredUpdateStrategy extends UpdateStrategy {
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

class DefaultUpdateStrategy extends UpdateStrategy {
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
