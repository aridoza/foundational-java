/**
 * Functions and Scope Homework
 * Created by charlie on 6/21/16.
 */
public class Main {

    public static String mStoreName;

    public static void main(String[] args) {

        // Pretend you're running an online store. Complete the "helper" methods below and call them here in main().

        // 1) Complete the setStoreName() method below and use it to save your store's name
        setStoreName("Charlie's Discount Warehouse");

        // 2) Complete the greetCustomer() method below
        String greeting = greetCustomer("Drew");
        System.out.println(greeting);


        // 3) Complete the getSalePrice() method below and make sure the correct value is printed here
        Double originalPrice = 79.99;
        Double discount = 0.25; // a 25% discount
        Double salePrice = getSalePrice(originalPrice, discount);
        System.out.println("25% off $79.99 is $" + salePrice);

        // you can also nest a call to getSalePrice() inside a call to println()
        System.out.println("15% off $24.95 is $" + getSalePrice(24.95, 0.15));


        // 4) Complete the getClearancePrice() method below and make sure correct value is printed here
        Double clearancePrice = getClearancePrice(originalPrice, discount);
        System.out.println("take an extra 50% off after the 25% discount from $79.99: $" + clearancePrice);


        // 5) Complete the getBulkDiscount() method below. Apply it to get a sale price.
        int numberOfItems = 12;
        double bulkDiscount = getBulkDiscount(numberOfItems);
        double itemPrice = 19.99;
        double salePriceForEachItem = getSalePrice(itemPrice, bulkDiscount);
        System.out.println("price after bulk discount: $" + salePriceForEachItem);

        // you can also nest all these method calls together:
        System.out.println("bulk discount price: $" + getSalePrice(100.0, getBulkDiscount(28)));


        // *** BONUS ***
        // 6) See if you can modify the methods that return a double so that the returned value is rounded
        //    to 2 decimal places (e.g. $59.99 rather than $59.992499999)
        //
        // hint - look for a rounding method in the Math class. You may need to combine that with some
        // additional arithmetic. There are other approaches too - feel free to google it.


        // ***BONUS***
        // 7) Complete the printInventoryList() method below and call it with different numbers of inputs
        printInventory("baseball cap", "cowboy hat", "fedora");
        printInventory("Nexus 5X", "Samsung S7", "iPhone 6S", "Nexus 6P", "One Plus 3", "Moto X");
    }


    /**
     * This method should update the class variable mStoreName by assigning it the value of the name input.
     *
     * @param name, as String
     */
    public static void setStoreName(String name) {
        // update the value of mStoreName, then explain in a comment why you can access that variable
        // from within the scope of this method, since mStoreName is NOT local to this method.

        mStoreName = name;
        // mStoreName is not local to this method, but it is a member variable for the whole Main class,
        // which makes it "global" to all the methods in the Main class
    }


    /**
     * This method should return "Hi ___, welcome to ___" where the first blank is the customer's name,
     * and the second blank is the store name you set using setStoreName().
     *
     * For example, greetCustomers("Jim") might return "Hi Jim, welcome to Bob's Discount Warehouse"
     *
     * @param customerName, a String
     * @return the completed String
     */
    public static String greetCustomer(String customerName) {
        // do some concatenation and return the result
        return "Hi " + customerName + ", welcome to " + mStoreName;
    }


    /**
     * This method should return the sale price of an item after the specified discount.
     * For example, getSalePrice(100.0, 0.2) should return 80.0 (the sale price after a 20% discount)
     *
     * @param fullPrice a double
     * @param discount a double that must be between 0.0 and 1.0
     * @return a double representing the sale price
     */
    public static double getSalePrice(double fullPrice, double discount) {
        // do some math and return the sale price

        // non-rounded version
        //return fullPrice * (1.0 - discount);

        // rounded version
        return Math.round(fullPrice * (1.0 - discount) * 100.0) / 100.0;
        // multiply by 100 to shift the decimal point 2 places, then round to nearest whole number,
        // then shift the decimal point back by dividing by 100
    }


    /**
     * This method should first calculate the sale price by applying the discount, then take off an additional 50%
     * For example, getClearancePrice(100.0, 0.2) should return 40.0 (20% off yields 80.0, then 50% off that yields 40.0)
     *
     * @param fullPrice a double
     * @param discount a double that must be between 0.0 and 1.0
     * @return a double representing the clearance price
     */
    public static double getClearancePrice(double fullPrice, double discount) {
        // A) calculate the sale price, then B) take off the extra 50% to get the clearance price

        // hint - consider the DRY principle: Don't Repeat Yourself!
        // can you use the getSalePrice() method here to avoid repeating logic you've already written?

        // non-rounded version
        //return getSalePrice(fullPrice, discount) * 0.5;

        // rounded version
        return Math.round(getSalePrice(fullPrice, discount) * 0.5 * 100.0) / 100.0;
    }


    /**
     * You want to give your customers a discount if they buy many of the same item. For every 5 items they
     * buy, you want to give 5% off:
     *
     * 1 to 4 of the same item: no discount
     * 5 to 9 of the same item: 5% discount (0.05 as a double)
     * 10 to 14 of the same item: 10% discount (0.1 as a double)
     * 15 to 19 of the same item: 15% discount (0.15 as a double)
     * etc.
     *
     * This method should return the discount to be applied. For example, getBulkDiscount(17) returns 0.15.
     *
     * @param quantityPurchased, and int
     * @return the discount level, a double between 0.0 and 1.0
     */
    public static double getBulkDiscount(int quantityPurchased) {
        // there are multiple ways to do this - we discussed a situation where the remainder from
        // division is truncated (removed). Can you use that to help you here?

        return (quantityPurchased / 5) * 0.05;
        // the expression (quantityPurchased / 5) involves two ints, so it will do int division and truncate
        // the remainder. Then you can multiply by 0.05 to get 5% discount for every 5 items purchased.
        // This will work for any quantity, even if it's 20 or greater.
    }


    /**
     * This method should print each item on the screen, all on one line. The number of items can vary.
     * They items can be separated by spaces, commas, slashes, or whatever delimiter you like.
     *
     * @param items, a sequence of Strings
     */
    public static void printInventory(String... items) {
        // In order to print each item you'll need to loop through all the items.
        // We haven't covered loops yet, so challenge yourself to figure it out by googling.
        // If you don't get it, no worries! We'll cover loops tomorrow.

        if (items.length == 0) {
            return; // if zero strings were provided as input, return without doing anything
        }

        // print the first input String
        System.out.print(items[0]); // print() doesn't move to the next line after printing like println()

        // loop through the rest of the input Strings
        for (int i = 1; i < items.length; i++) { // start at index 1, the second position of the array
            System.out.print(", " + items[i]);
        }

        // finally, use println() to move the cursor to the next line
        System.out.println(); // you could also do System.out.print("\n")
    }
}
