# ** Gilded Rose **

This project is a part of Software Quality and Testing course at the University of Oulu. The project is a test writing 
and refactoring exercise. This is forked from the original repository at https://github.com/M3SOulu/GildedRose2020.git.

## ** Project structure changes **

The project structure has been changed to a maven project for personal preference. There was a need to add a pom.xml file
to the project to make it a maven project. There is also a slight change in the directory structure to make it follow
the maven project structure.

## ** First implementation of tests **

There was 2 test classes already implemented in the project. The first test is testTheTruth() which is a simple test to
check if the test framework is working. The second test is exampleTest() which is a test to check if the example item
is working as expected. The test checks if the items quality is decreasing by 1 after a day. Since there was just 2 tests
implemented, I decided to implement more tests before diving into code coverage since the tests already implemented 
seemed to be working fine when I ran maven first time.

For the first implementation of test I decided to concentrate on the listed items and making sure sellIn and quality 
values are changing as expected after one day is simulated. I implemented tests for each item and made sure the values
are changing as expected. I also decide to put the exampleTest() inside comment since I wanted to see the results of
the tests I implemented personally. I decided keep the testTheTruth() test for sanity check as intended.

As a rule of thumb, most of the items quality decreases by 1 after a day and sellIn decreases by 1 as well. There are
exceptions to this rule which are Aged Brie, Backstage passes and Sulfuras. Aged Brie quality increases instead of
decreasing. Backstage passes behave similar to Aged Brie but the quality increases by 2 when sellIn is 10 or less and
by 3 when sellIn is 5 or less. Sulfuras is a legendary item and it never has to be sold or decreases in quality.


## ** Code coverage **

After implementing the tests, I decided to check the code coverage of the tests. I used JaCoCo plugin for maven to check
the code coverage. 7/7 tests passed, but code coverage was just 48% by lines, 47% by branches and 59% of methods. So there
is a lot of room for improvement in the code coverage. Code coverage report can be found below.

![Code coverage report](/images/CoverageReportR1.png)

## ** Coverage Rates **

### Coverage Rate by Lines

- Coverage Rate: 48%
- Covered Lines: 171
- Missed Lines: 180
- Total Lines: 351

### Coverage Rate by Branches

- Coverage Rate: 47%
- Covered Branches: 17
- Missed Branches: 19
- Total Branches: 36

### Coverage Rate by Methods

- Coverage Rate: 59%
- Covered Methods: 13
- Missed Methods: 9
- Total Methods: 22


## ** Second implementation of tests **

After implementing test to check listed items basic functionality i decided to start implementing tests for more complex
cases like pushing the limits of the items and checking if the items are behaving as expected. There is also some items
with special rules that need more specific tests to make sure they are working as expected. While adding these tests I
decided it would be good to add some tests for certain limitations of the items mentioned in the pdf file provided with
the project.

1. Additional tests for items without special rules.

I decided to add tests for items without special rules to make sure they are behaving as expected. I added tests for
items like Dexterity Vest, Elixir of the Mongoose and Conjured Mana Cake. I added tests to check if the quality decreases
by 2 after the sellIn date has passed and sellIn passes 0. I ended up using a for loop to simulate the days passing and
checking the quality drop after the sellIn date has passed. This might not be the most efficient way to do this but it
gives a opportunity to also test the quality drop rate mechanism as intended, while keeping the item stats in original
form. With the same logic I also added tests to check if the quality can drop below 0. In here i could alter the item
values, but i decided to keep the item values in original form and kept the for loop solution to simulate the days passing.

Example test for Dexterity Vest Quality drop rate:

```java
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
```

Example test for Elixir of the Mongoose Negative Quality:

```java
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
```

2. Additional tests for items with special rules.

Since there are 3 different items with special rules, and they all have different rules, I go through them one by one to
present the tests implemented for them. First the Aged Brie, which quality should increase older it gets. I added test
to check if the quality keeps increasing after a sellIn value passes 0. I also added another test to check if the quality
can go above 50, since it was mentioned in the pdf file that the quality can't go above 50.

Test for Aged Brie Quality Increase adter sellIn passes 0:

```java
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
```

Running the test for Aged Brie Quality Increase after sellIn did not pass, and caused a next error message:

![Aged Brie Quality Increase error message](/images/AgedBrieQualityRate.png)

As we can see the quality is not increasing as intended and the quality improvement rate doubles after the sellIn
passes 0. After double-checking the instructions for the Aged Brie, there was no mention of the quality improvement
rate doubling after the sellIn passes 0. When going through the code, I found the part of the code that was causing the
bug from line 96 to 103 in GildedRose.java file. I decided to cut the part of the code by placing it inside of comment
and run the test again. The test passed as intended and the quality was increasing by 1 after the sellIn passes 0.

After fixing the Aged Brie quality increase rate, I decided to run the test for Aged Brie Quality Increase above 50.
The test passed as intended and the quality did not go above 50 as intended. Test can be found below:

```java
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
```

Next item with special rules is Sulfuras, Hand of Ragnaros. Sulfuras is a legendary item and it never has to be sold or
decreases in quality. I added a test to check if the sellIn and quality values are not changing after multiple days
passing. The test passed as intended and the values did not change as intended. Test can be found below:

```java
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
```

Last item with special rules is Backstage passes to a TAFKAL80ETC concert. Backstage passes behave similar to Aged Brie
but the quality increases by 2 when sellIn is 10 or less and by 3 when sellIn is 5 or less. When sellIn passes 0 the
quality drops to 0. I added tests to check if the quality increases by 2 when sellIn is 10 or less and by 3 when sellIn
is 5 or less. I also added a test to check if the quality drops to 0 after sellIn passes 0. After these I decided to check
that the quality can't go above 50 with the backstage passes as well. All the tests passed as intended and the quality
was behaving as expected. Tests can be found below:

Test for Backstage passes to a TAFKAL80ETC concert Quality Increase by 2 when sellIn is 10 or less:

```java
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
```

Test for Backstage passes to a TAFKAL80ETC concert Quality Increase by 3 when sellIn is 5 or less:

```java
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
```

Test for Backstage passes to a TAFKAL80ETC concert Quality Drop to 0 after sellIn passes 0:

```java
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
```

Test for Backstage passes to a TAFKAL80ETC concert Quality Increase above 50:
NOTE! This is only test so far where I had to alter the item values from originals to make the test work as intended!

```java
@Test
	public void testBackStagePassesMaxQuality() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 49));
		for (int i = 0; i < 2; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		assertEquals("Failed quality for Backstage passes", 50, quality);
	}
```

After implementing the tests for the items with special rules, I decided to run the code coverage again to see if the
coverage has improved.

## ** Coverage Rates **

![Code coverage report](/images/CoverageReportR2.png)

### Coverage Rate by Lines

- Coverage Rate: 100%
- Covered Lines: 331
- Missed Lines: 0
- Total Lines: 331

### Coverage Rate by Branches

- Coverage Rate: 91%
- Covered Branches: 31
- Missed Branches: 3
- Total Branches: 34

### Coverage Rate by Methods

- Coverage Rate: 100%
- Covered Methods: 14
- Missed Methods: 0
- Total Methods: 14

As we can see the code coverage has improved significantly after the second implementation of tests. The coverage rate by
lines is now 100%, by branches 91% and by methods 100%. The tests are now covering all the lines and methods in the code
and most of the branches as well. There is still some room for improvement in the branch coverage, but the coverage is
now at a good level.

## ** Reaching 100% coverage **

After reaching 100% coverage by lines and methods, I decided to try to reach 100% coverage by branches as well. After 
reviewing and diving deeper in the report I discovered next things:

![Missed Branches in updateQuality](/images/updateQuality_Missed.png)

![Missed Branches in updateQuality2](/images/Missing_Branches.png)

After evaluating this information and going through the lines in my IDE i found out there was no false hits produced by
the test on these particular lines. After going trough tests i had implemented before, i realised that the test for 
Backstage pass max quality was not covering the lines in the updateQuality method. after altering the sellIn value to 5
in the test, it now covers the missing branches. The test can be found below:

```java
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
```

But after this i was still missing 1 branch in the updateQuality method. After going through the tests again I realised
that I had missed a test for Sulfuras keeping its quality if it gets sold. I added a test for this and the coverage was
now 100% by branches as well. The test can be found below:

```java
@Test
	public void testSulfurasQualityNotDecreaseWhenSellInNegative() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", -1, 80));
		inn.oneDay();
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		assertEquals("Failed quality for Sulfuras, Hand of Ragnaros", 80, quality);
	}
```

After implementing the test for Sulfuras quality not decreasing when sellIn is negative, the coverage was now 100% by
branches as well. The final coverage report can be found below:

![Final Code coverage report](/images/100_CoverageReached.png)


## ** Loop testing **

In the given instructions by the course teachers there is a task to test for loop GildedRose has in updateQuality() 
method, the exact instructions are as follows:

"Study the for-loop GildedRose has in method updateQuality(). Check that your current tests cover all the steps required
in Loop Testing. Is it possible to cover all the steps required in Loop Testing without modifying the SUT code? 
Write new tests until you cover the loop.  Is there a change in the code coverage metrics?"

After studying the for-loop in the updateQuality() method, I can break down the behavior of the loop as follows:

- The method iterates over each item in the items list.
- It updates the quality and sellIn values based on specific rules for different types of items.
- Special rules are applied to Aged Brie, Backstage passes and Sulfuras.
- The quality of an item is never more than 50 or less than 0 (except for "Sulfuras, Hand of Ragnaros", which always has
  a quality of 80).

When going trough my tests I realised that I had already covered the loop in the updateQuality() method with the tests
I had implemented. The tests were already covering all the steps required in Loop Testing, and only alteration I had to
do at SUT code was to cut out part of the code that was causing a bug in the Aged Brie quality increase rate as mentioned 
earlier. There is a only one test for a specific case that I can think and that is testing the updateQuality() method with
no items in the list. I decided to implement this test to make sure the method is working as intended when there is no
items in the list. The test can be found below:

```java
@Test
	public void testUpdateQualityWithNoItems() {
		GildedRose inn = new GildedRose();
		inn.oneDay();
		List<Item> items = inn.getItems();
		assertTrue("Items list should be empty", items.isEmpty());
	}
```

After re-running the test after latest implementation, the coverage rates were still at 100% as before. 


