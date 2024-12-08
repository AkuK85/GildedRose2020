package fi.oulu.tol.sqat;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {


	@Test
	public void testTheTruth() {
		assertTrue(true);
	}

	/*
	@Test
	public void exampleTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}
	*/


	@Test
	public void testDexterityVest() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		int quality = items.get(0).getQuality();
		assertEquals("Failed sellIn for Dexterity Vest", 9, sellIn);
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}

	@Test
	public void testDexterityVestQualityRateCheck() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		for (int i = 0; i < 11; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		int quality = items.get(0).getQuality();
		assertEquals("Failed sellIn for Dexterity Vest", -1, sellIn);
		assertEquals("Failed quality for Dexterity Vest", 8, quality);
	}

	@Test
	public void testDexterityVestNegativeQuality() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		for (int i = 0; i < 16; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		assertEquals("Failed quality for Dexterity Vest", 0, quality);
	}

	@Test
	public void testAgedBrie() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 2, 0));
		inn.oneDay();
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		int quality = items.get(0).getQuality();
		assertEquals("Failed sellIn for Aged Brie", 1, sellIn);
		assertEquals("Failed quality for Aged Brie", 1, quality);
	}

	@Test
	public void testAgedBrieQualityRateCheck() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 2, 0));
		for (int i = 0; i < 3; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		int quality = items.get(0).getQuality();
		assertEquals("Failed sellIn for Aged Brie", -1, sellIn);
		assertEquals("Failed quality for Aged Brie", 3, quality);
	}

	@Test
	public void testAgedBrieMaxQuality() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 2, 0));
		for (int i = 0; i < 51; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		assertEquals("Failed quality for Aged Brie", 50, quality);
	}

	@Test
	public void testElixirOfTheMongoose() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Elixir of the Mongoose", 5, 7));
		inn.oneDay();
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		int quality = items.get(0).getQuality();
		assertEquals("Failed sellIn for Elixir of the Mongoose", 4, sellIn);
		assertEquals("Failed quality for Elixir of the Mongoose", 6, quality);
	}

	@Test
	public void testElixirOfTHeMongooseQualityRateCheck() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Elixir of the Mongoose", 5, 7));
		for (int i = 0; i < 6; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		int quality = items.get(0).getQuality();
		assertEquals("Failed sellIn for Elixir of the Mongoose", -1, sellIn);
		assertEquals("Failed quality for Elixir of the Mongoose", 0, quality);
	}

	@Test
	public void testElixirOfTheMongooseNegativeQuality() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Elixir of the Mongoose", 5, 7));
		for (int i = 0; i < 7; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		assertEquals("Failed quality for Elixir of the Mongoose", 0, quality);
	}

	@Test
	public void testSulfuras() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item ("Sulfuras, Hand of Ragnaros", 0, 80));
		inn.oneDay();
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		int quality = items.get(0).getQuality();
		assertEquals("Failed sellIn for Sulfuras, Hand of Ragnaros", 0, sellIn);
		assertEquals("Failed quality for Sulfuras, Hand of Ragnaros", 80, quality);
	}

	@Test
	public void testSulfurasKeepUnchanged() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		for (int i = 0; i < 10; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		int quality = items.get(0).getQuality();
		assertEquals("Failed sellIn for Sulfuras, Hand of Ragnaros", 0, sellIn);
		assertEquals("Failed quality for Sulfuras, Hand of Ragnaros", 80, quality);
	}

	@Test
	public void testBackStagePasses() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		inn.oneDay();
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		int quality = items.get(0).getQuality();
		assertEquals("Failed sellIn for Backstage passes", 14, sellIn);
		assertEquals("Failed quality for Backstage passes", 21, quality);
	}

	@Test
	public void testBackStagePassesSellInAfter6daysPassed() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		for (int i = 0; i < 6; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		int quality = items.get(0).getQuality();
		assertEquals("Failed sellIn for Backstage passes", 9, sellIn);
		assertEquals("Failed quality for Backstage passes", 27, quality);
	}

	@Test
	public void testBackStagePassesSellInAfter11daysPassed() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		for (int i = 0; i < 11; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		int quality = items.get(0).getQuality();
		assertEquals("Failed sellIn for Backstage passes", 4, sellIn);
		assertEquals("Failed quality for Backstage passes", 38, quality);
	}

	@Test
	public void testBackStagePassesSellInAfterConcert() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		for (int i = 0; i < 16; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		int quality = items.get(0).getQuality();
		assertEquals("Failed sellIn for Backstage passes", -1, sellIn);
		assertEquals("Failed quality for Backstage passes", 0, quality);
	}

	@Test
	public void testBackStagePassesMaxQuality() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49));
		for (int i = 0; i < 2; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		assertEquals("Failed quality for Backstage passes", 50, quality);
	}

	@Test
	public void testConjuredManaCake() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Conjured Mana Cake", 3, 6));
		inn.oneDay();
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		int quality = items.get(0).getQuality();
		assertEquals("Failed sellIn for Conjured Mana Cake", 2, sellIn);
		assertEquals("Failed quality for Conjured Mana Cake", 5, quality);
	}

	@Test
	public void testConjuredManaCakeQualityRateCheck() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Conjured Mana Cake", 3, 6));
		for (int i = 0; i < 4; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		int quality = items.get(0).getQuality();
		assertEquals("Failed sellIn for Conjured Mana Cake", -1, sellIn);
		assertEquals("Failed quality for Conjured Mana Cake", 1, quality);
	}

	@Test
	public void testConjuredManaCakeNegativeQuality() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Conjured Mana Cake", 3, 6));
		for (int i = 0; i < 5; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		assertEquals("Failed quality for Conjured Mana Cake", 0, quality);
	}

	@Test
	public void testSulfurasQualityNotDecreaseWhenSellInNegative() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", -1, 80));
		inn.oneDay();
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		assertEquals("Failed quality for Sulfuras, Hand of Ragnaros", 80, quality);
	}







}
