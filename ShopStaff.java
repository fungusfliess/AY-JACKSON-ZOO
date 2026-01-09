/*
   File Name: ShopStaff.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: 
*/

public class ShopStaff {
    public static void FACTS_BONUS_PERCENTAGE = 0.02; 
    private int itemsSold; 
    private double totalSales; 
    private int factsShared; 

    public ShopStaff(int age, String personID, String firstName, String lastName, double hourlyWage, int yearsOfExperience){
        super(age, personID, firstName, lastName, hourlyWage, yearsOfExperience);
        itemsSold = 0; 
        totalSales = 0.0; 
        factsShared = 0; 
    }

    public int getItemsSold(){return itemsSold;}
    public double getTotalSales(){return totalSales;}
    public int getFactsShared(){return factsShared;}

    public void setEarnings(){
        earnings = hourlyWage*hoursWorked*(1+ factsShared*FACTS_BONUS_PERCENTAGE);
    }

    public String getRole(){
        return "SHOPSTAFF";
    }
    
    public boolean sellItem(Item item, Visitor visitor){
        if (visitor!=null && visitor.canAffordItem(item)){
            balance -= item.getPrice(); 
            visitor.addItem(item);
            itemsSold++; 
            totalSales+=item.getPrice(); 
            return true; 
        } 
        return false; 
    }

    public String toString(){
        return "PersonID: " + personID + "\n" + 
                "Name: " + firstName + " " + lastName + "\n" + 
                "Age: " + age + "\n" + 
                "Role: " + this.getRole() + "\n" + 
                "Num Items Sold: " + certificationLevel + "\n" + 
                "Num Facts Shared: " + dailyTasksCompleted + "\n" + 
                "Earnings: " + earnings + "\n"; 
    }

    public void passDay(){
        super(); 
        itemsSold = 0; 
        totalSales = 0.0; 
        factsShared = 0; 
    }
}
