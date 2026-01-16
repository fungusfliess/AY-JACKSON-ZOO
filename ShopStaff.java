/*
   File Name: ShopStaff.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: ShopStaff models employees who work in the zoo gift shop or admissions-related tasks. It also inherits wages/experience/earnings 
                from Employee and returns a role identifier like "SHOPSTAFF". It is stored in the zoo’s employee list and can be sorted by 
                earnings or experience.
*/
public class ShopStaff extends Employee {

    //CONSTANTS
    public static final double FACTS_BONUS_PERCENTAGE = 0.02;

    //FIELDS
    private int itemsSold;
    private double totalSales;
    private int factsShared;

    //CONSTRUCTOR
    /*
     @description: declares and initializes ShopStaff object
     @param age               the employee's age
     @param personID          unique ID for the employee
     @param firstName         the employee's first name
     @param lastName          the employee's last name
     @param hourlyWage        the employee's hourly wage
     @param yearsOfExperience the employee's years of experience
     */
    public ShopStaff(int age, String personID, String firstName, String lastName,
                     double hourlyWage, int yearsOfExperience) {
        super(age, personID, firstName, lastName, hourlyWage, yearsOfExperience);
        itemsSold = 0;
        totalSales = 0.0;
        factsShared = 0;
    }

    //ACCESSOR
    public int getItemsSold() { return itemsSold; }
    public double getTotalSales() { return totalSales; }
    public int getFactsShared() { return factsShared; }

    /*
     @description: updates the employee's total earnings for the day using ShopStaff pay rules
     @note: day pay = hourlyWage * hoursWorked * (1 + factsShared * FACTS_BONUS_PERCENTAGE)
    */
    @Override
    public void setEarnings() {
        double multiplier = 1 + factsShared * FACTS_BONUS_PERCENTAGE;
        double dayPay = getHourlyWage() * getHoursWorked() * multiplier;

        // requires Employee to have protected addToEarnings(double)
        addToEarnings(dayPay);
    }

    /*
     @return the role string for this Person object
    */
    @Override
    public String getRole() {
        return "SHOPSTAFF";
    }

    /*
     @description: sells an item to a visitor if the visitor can afford it
     @param item    the item being sold
     @param visitor the visitor buying the item
     @return true if the sale was successful, false otherwise
    */
    public boolean sellItem(Item item, Visitor visitor) {
        if (item == null || visitor == null) return false;
        if (!visitor.canAffordItem(item)) return false;

        // Let Visitor update its own balance/spending
        visitor.recordPurchase(item.getPrice());
        visitor.addItem(item);

        itemsSold++;
        totalSales += item.getPrice();
        return true;
    }

    /*
     @description: returns a formatted string summary of this ShopStaff employee
     @return formatted employee information
    */
    @Override
    public String toString() {
        return "PersonID: " + getPersonID() + "\n" +
               "Name: " + getFirstName() + " " + getLastName() + "\n" +
               "Age: " + getAge() + "\n" +
               "Role: " + getRole() + "\n" +
               "Items Sold: " + itemsSold + "\n" +
               "Total Sales: " + totalSales + "\n" +
               "Facts Shared: " + factsShared + "\n" +
               "Earnings: " + getEarnings() + "\n";
    }

    /*
     @description: ends the day for ShopStaff by calculating earnings, resetting hours worked (in Employee),
                   and resetting ShopStaff daily counters
    */
    @Override
    public void passDay() {
        super.passDay();
        itemsSold = 0;
        totalSales = 0.0;
        factsShared = 0;
    }

    /*
     @description: increases the number of facts shared by 1
     */
    public void addFactShared() {
        factsShared++;
    }

}