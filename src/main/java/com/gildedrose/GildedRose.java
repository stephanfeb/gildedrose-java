package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private boolean isAgedBrie(Item item){
        return item.name.equals("Aged Brie");
    }

    private boolean isBackstagePass(Item item){
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            //handle products whose quality is affected by age
            if (isAgingProduct(items[i])) {
                if (items[i].quality > 0) {
                    decreaseQuality(items[i]) ;
                }
            } else {
                    increaseQuality(items[i]);

                    if (isBackstagePass(items[i])) {
                        if (items[i].sellIn < 11) {
                            increaseQuality(items[i]);
                        }

                        if (items[i].sellIn < 6) {
                            increaseQuality(items[i]);
                        }
                    }
            }

            //no effect by age on quality of this item
            if (!isSulfuras(items[i])) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            updateExpiredItems(items[i]);
        }
    }

    private boolean isAgingProduct(Item item) {
        return !isAgedBrie(item) && !isBackstagePass(item);
    }

    private void updateExpiredItems(Item item) {
        if (item.sellIn < 0) {
            if (isAgedBrie(item)) {
                increaseQuality(item);
            } else {

                if (isBackstagePass(item)) {
                   zeroItemQuality(item) ;
                } else {
                    if (item.quality > 0) {
                        decreaseQuality(item);
                    }
                }
            }
        }
    }

    private void zeroItemQuality(Item item) {
        item.quality = item.quality - item.quality;
    }

    private void decreaseQuality(Item item) {

        if (!isSulfuras(item)) {
            item.quality = item.quality - 1;
        }

        if (isConjuredProduct(item)){
            item.quality = item.quality - 1; //additional decrement for conjured products
        }
    }

    private boolean isConjuredProduct(Item item) {
        return item.name.equals("Conjured Mana Cake");
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

}
