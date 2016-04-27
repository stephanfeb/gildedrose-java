package com.gildedrose;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Ignore;
import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void qualityOfBrieIncreasesAfterSellby(){
        Item[] items = new Item[]{new Item("Aged Brie", -1, 1)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.getItem(0).quality, equalTo(3));
    }

    @Test
    public void qualityOfItemIsNeverNegative(){

        Item[] items = new Item[]{new Item("Elixir of the Mongoose", -1, 2)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality(); //update thrice to see if quality dips below zero
        assertTrue(app.getItem(0).quality > -1 );
    }


    @Test
    public void qualityOfRegularItemDecreases(){

        Item[] items = new Item[]{new Item("Elixir of the Mongoose", 1, 5)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.getItem(0).quality, equalTo(4));
    }

    @Test
    public void qualityIsNeverGreaterThan50(){

        Item[] items = new Item[]{new Item("Aged Brie", 1, 50)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.getItem(0).quality, equalTo(50));
    }

    @Test
    public void sulfurasQualityNeverChanges(){

        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 1, 40)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.getItem(0).quality, equalTo(40));
    }

    @Test
    public void backstagePassesIncreaseQuality(){

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 11, 40)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.getItem(0).quality, equalTo(41));
    }


    @Test
    public void backstagePassesDoubleIncreaseInTenDays(){

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 9, 40)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.getItem(0).quality, equalTo(42));
    }

    @Test
    public void backstagePassesInThreeDays(){

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 3, 40)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.getItem(0).quality, equalTo(43));
    }


    @Test
    public void backstagePassesZeroAfterDate(){

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", -1, 40)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.getItem(0).quality, equalTo(0));
    }

    /*
        * This is the failing test you're meant to get working.
        * Conjured items are supposed to degrade twice the rate (by two points)
        * */
    @Test
    public void conjuredItemsDoubleDegradation(){

        Item[] items = new Item[]{new Item("Conjured Mana Cake", 10, 40)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.getItem(0).quality, equalTo(38));
    }
    
    
}
