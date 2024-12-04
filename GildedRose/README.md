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

