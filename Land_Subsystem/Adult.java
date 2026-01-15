/*
   File Name: Adult.java
   Name: Elizabeth Wang
   Class: ICS4U1-11
   Date: Jan 7, 2025
   Description: Adult represents standard visitors who pay the regular admission price. It uses the base visitor fields for balance/learning 
                tracking and returns "ADULT" as the role. Its ticket cost is calculated using the adult base price rules in your system.
*/

import Person_Subsystem.Visitor;

public abstract class Adult extends Visitor{
   //FIELDS 
   private double preferredBudgetLimit; 

   //CONSTRUCTOR
   /*
    @description: declares and initializes Adult visitor object
    @param age                 the visitor's age
    @param personID            unique ID for the visitor
    @param firstName           the visitor's first name
    @param lastName            the visitor's last name
    @param balance             starting balance (money available)
    @param learningLevel       starting learning level
    @param visitDuration       visit duration (in minutes or your chosen unit)
    @param preferredBudgetLimit the visitor's preferred spending limit (if negative, set to 0)
    */
   public Adult(int age, String personID, String firstName, String lastName, double balance, int learningLevel, int visitDuration, double preferredBudgetLimit) {
         super(age, personID, firstName, lastName, balance, learningLevel, visitDuration);
         this.preferredBudgetLimit = Math.max(0.0, preferredBudgetLimit);
   }

   //ACCESSOR
   public double getPreferredBudgetLimit(){
      return preferredBudgetLimit;
   }

   //MUTATOR
   public void setPreferredBudgetLimit(double limit){
      this.preferredBudgetLimit = limit;
   }

   //OTHER METHODS 
   /*
    @description: checks if the visitor stayed within their preferred budget limit
    @return true if amount spent is less than the preferred budget limit, false otherwise
    */
   public boolean stayedWithinBudget(){
      return getAmountSpent()<preferredBudgetLimit; 
   }

   /*
    @return the role string for this Person object
    */
   @Override
   public String getRole(){
      return "ADULT";
   }

   //EXPAND SUMMARY LATWR 
   /*
    @description: returns a summary message when the visitor leaves the zoo
    @return a summary string including whether the visitor stayed within budget
    */
   public String endVisitorSummary(){
      this.endVisitorSummary(); 
      if (stayedWithinBudget()){
         return "You stayed within your budget.\n";
      } else {
         return "You exceeded your budget.\n";
      }
   }


   /*
    @description: calculates the ticket cost for an Adult visitor
    @return the admission price for an Adult visitor
    */
   @Override
   public double calculateTicketCost(){
      return ADULT_BASE_PRICE;
   }

   /*
    @description: returns a formatted string summary of this Adult visitor
    @return formatted visitor information
    */
   @Override
   public String toString(){
      return "PersonID: " + personID + "\n" + 
              "Name: " + firstName + " " + lastName + "\n" + 
              "Age: " + age + "\n" + 
              "Role: " + this.getRole() + "\n" + 
              "Balance: " + balance + "\n" + 
              "Learning Level: " + learningLevel + "\n" + 
              "Attractions Visited: " + attractionsVisited + "\n" +
              "Num Facts Learned: " + learningHistorySize + "\n" +
              "Preferred Budget Limit: " + preferredBudgetLimit + "\n"; 
   }

}
