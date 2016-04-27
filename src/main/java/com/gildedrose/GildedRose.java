package com.gildedrose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GildedRose {
    private List<Item> items;

    public GildedRose(Item[] items) {
        this.items = Arrays.asList(items);
    }

    public Item getItem(int index){
        return items.get(index);
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
        for (Item item: items) {
            //handle products whose quality is affected by age
            if (isAgingProduct(item)) {
                if (item.quality > 0) {
                    decreaseQuality(item) ;
                }
            } else {
                    increaseQuality(item);

                    if (isBackstagePass(item)) {
                        if (item.sellIn < 11) {
                            increaseQuality(item);
                        }

                        if (item.sellIn < 6) {
                            increaseQuality(item);
                        }
                    }
            }

            //no effect by age on quality of this item
            if (!isSulfuras(item)) {
                item.sellIn = item.sellIn - 1;
            }

            updateExpiredItems(item);
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
