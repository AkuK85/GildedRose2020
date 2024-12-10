package fi.oulu.tol.sqat;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
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

	@Test
	public void testUpdateQualityWithNoItems() {
		GildedRose inn = new GildedRose();
		inn.oneDay();
		List<Item> items = inn.getItems();
		assertTrue("Items list should be empty", items.isEmpty());
	}

	@Test
	public void testMainMethod() throws NoSuchFieldException, IllegalAccessException {

		// Capture the console output
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));

		try {
			// Call the main method
			GildedRose.main(new String[]{});
			// Verify the console output
			assertTrue(outContent.toString().contains("OMGHAI!"));
			// Use reflection to access the private items field
			Field itemsField = GildedRose.class.getDeclaredField("items");
			itemsField.setAccessible(true);
			List<Item> items = (List<Item>) itemsField.get(null);
			// Verify the state of the items list
			assertNotNull(items);
			assertEquals(6, items.size());
			// Verify the properties of the items
			assertEquals("+5 Dexterity Vest", items.get(0).getName());
			assertEquals(9, items.get(0).getSellIn());
			assertEquals(19, items.get(0).getQuality());

			assertEquals("Aged Brie", items.get(1).getName());
			assertEquals(1, items.get(1).getSellIn());
			assertEquals(1, items.get(1).getQuality());

			assertEquals("Elixir of the Mongoose", items.get(2).getName());
			assertEquals(4, items.get(2).getSellIn());
			assertEquals(6, items.get(2).getQuality());

			assertEquals("Sulfuras, Hand of Ragnaros", items.get(3).getName());
			assertEquals(0, items.get(3).getSellIn());
			assertEquals(80, items.get(3).getQuality());

			assertEquals("Backstage passes to a TAFKAL80ETC concert", items.get(4).getName());
			assertEquals(14, items.get(4).getSellIn());
			assertEquals(21, items.get(4).getQuality());

			assertEquals("Conjured Mana Cake", items.get(5).getName());
			assertEquals(2, items.get(5).getSellIn());
			assertEquals(5, items.get(5).getQuality());
		} finally {
			// Restore the original console output
			System.setOut(originalOut);
		}
	}


}

