# UncleBobVideoStore
The video store example from Episode 3 of cleancoders.com. Based upon, but not identical to, the first chapter of Martin Fowler's classic book: Refactoring.

## JAD
I made some changes from the original
- Made a Maven project
- Migrated the original text written in Junit 3 to Junit5

## Step 1
### Observe
- Very old code like written by C++ programmer
- Variables in some classes are placed at bottom
- Test class has long lines which make us scroll horizontally right
- Yuck
- Tests pass

### Activity
- Break the strings in the test methods into multiple parts
- Makes methods visible all in one screen
- No scrolling to right needed

## Step 2
### Observe
- All test methods are testing UI that is actual report
- All they need to test is the amounts calculated
- UI string test can be there in one of the tests for completeness
- Repetition of UI strings introduce regression impact

### Activity
- Move all variables in all classes to top
- Add assertions with code as below
```
assertEquals(9.0, customer.getTotalAmount(), DELTA);
assertEquals(2, customer.getFrequentRenterPoints());
```
- Create the necessary methods required with IDE intellisense
- Promote variables `totalAmount` and `frequentRenterPoints` to fields, keep init in current method
- Remove the string comparison in first test
- Just execute `customer.statement();` to generate them

## Step 3
### Observe
- `Customer` class is just generating a statement
- It has nothing to do with a customer other than maintaining a variable of `Customer customerName`

### Activity
- Rename `Customer` class to `Statement`
- Rename `name` in `Statement` class to `customerName`
- Rename `statement` method to `generate`
- Change all test methods to assert calculated values
- Duplicate last test to maintain one of them as testing string
- Add suffix `format` to the last method
- Add suffix `Totals` to all other test methods
- Keep the string comparison as is in last method

## Step 4
### Activity
- Change `Fred` to `Customer` in customer name
- Change the format method to reflect this change
- extract field for `new Movie("The Cell", Movie.NEW_RELEASE)` with name `newReleaseMovie1`
- extract `new Movie("The Tigger Movie", Movie.NEW_RELEASE)` into field `newReleaseMovie2`
- change title of `The Cell` to `New Release 1`
- change title of `The Tiger Movie` to `New Release 2`
- change all movies to variables and titles to generic titles
- change the format method to reflect the changes

## Step 5
### Activity
- generate method of the `Statement` class
- extract the statements which initialize to method named `initialize()`
```
totalAmount = 0;
frequentRenterPoints = 0;
```
- Rename result variable to `statementText`
- extract method for `createHeader()`
- Change return value of `createHeader()` to `MessageFormat.format()`

## Step 6
### Observe
- `while` loop in the generate method just adds rental lines to the statement

### Activity
- Extract `while` loop in method named `createRentalLines()`
- Remove the params and eliminate side effects
- Rename `statementText` to `rentalLinesText` in `createRentalLines()` method
- Combine footer test strings and format to multiple lines
- Extract footer logic to `createFooter()`
- Rename `initialize()` method to `clearTotals()`
- Change return value in `createFooter()` to `MessageFormat.format()`
- Change the order of methods in order of calls
- Step down method of ordering methods
- Remove unused `getCustomerName()` method
- Extract body of `while` loop from method `createRentalLines()` to method named `createRentalLine()`
- Rename variable `rentalLinesText` to `rentalLineText` in method `createRentalLine()`
- Rename parameter `each` to `rental` in method `createRentalLine()`

## Step 7
### Activity
- Extract the switch statement into method named `determineAmount()` using comment content for `name`
- Do not pass `thisAmount` variable
- Rename variable `thisAmount` to `rentalAmount` in method `determineAmount` and `createRentalLine`
- Extract `frequentRenterPoints` login into method `determineFrequentRenterPoints`
- Modify method to make it simpler and return value without side effect

## Step 8
### Activity
- Extract condition in `determineFrequentRenterPoints` method  into a variable
- Name it `bonusIsEarned`
- Rearrange all calculations at top in `createRentalLine`
- Extract formatting of rental lines into method `formatRentalLines`
- Remove variable declaration
- Change return value to `MessageFormat.format()` call
- Inline variable for `createRentalLine()` method return value
- Remove demeter law violation for `rental.getMovie().getTitle()`
- In method `formatRentalLines`
- Making method call like `rental.getTitle()`
- Rearrange methods of Statement class for step down method

## Step 9
### Observe
- `determineAmount()` method is not cohesive
- It does not use any fields in statement class
- It does not belong to `Statement` class
- In fact, it should belong to `Rental` class as it uses methods from that class
- same true with method `determineFrequentRenterPoints`

### Activity
- Move the method `determineAmount()` to `Rental` class as public
- change calls to `getMovie()` and `getDaysRented()` to field variables
- Move `determineFrequentRenterPoints` method to `Rental` class
- change calls to `getMovie()` and `getDaysRented()` to field variables

## Step 10
### Observe
- In `Rental` class `determineAmount()`, we use four things from movie and only one thing from `Rental`
- This method hence belongs to `Movie` class
- Same with method `determineFrequentRenterPoints()`

### Activity
- Add signatures of method like follows
```
public double determineAmount() {
    return determineAmount(daysRented);
}
public double determineAmount(int daysRented) {
...
}
```
- Move the method with argument to `Movie` class
- Fix the rental this issue
- Same Move with `determineFrequentRenterPoints()` method to `Movie`
- Inline `priceCode` in both methods

## Step 11
### Observe
- `determineAmount()` in `Movie` is using switch
- it should use polymorphic dispatch
- we have to do this without breaking code

### Activity
- In `Test` class
- Change the `Movie` class names to subclasses to create them with IDE
- remove the second parameter from constructors as it is redundant now
- In `Movie` class for `determineAmount()` method
- use refactoring Push Members Down, keep in `Movie` as `abstract`
- Run with coverage and remove unused code from all subclasses
- Remove the type codes of `Movies` now as they are redundant
- Change signature of constructor of `Movie` and remove `priceCode`

## Step 12 - JAD Bonus
### Activity
 - Go to settings > Editor > Code inspection > Code style issues
 - check instance field access not qualified with `this`
 - check instance method access not qualified with `this`
 - Run code analysis on `videostore` package
 - Fix all issues
