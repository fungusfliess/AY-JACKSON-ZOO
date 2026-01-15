package Person_Subsystem;
/*
   File Name: Senior.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Senior represents older visitors who may receive a senior discount and can require accessibility support. It stores a boolean 
                like requiresAccessibilitySupport and uses senior pricing rules (including free admission if your program has a free-senior 
                age threshold). It returns "SENIOR" as the role and includes accessibility status in its summary output.
*/

public abstract class Senior extends Adult {
   //CONSTANTS 
   public static final double SENIOR_DISCOUNT = 0.20; 

   //FIELDS
   private boolean requiresAccessibilitySupport; 

   //CONSTRUCTOR
   /*
    @description: declares and initializes Senior visitor object
    @param age                 the visitor's age
    @param personID            unique ID for the visitor
    @param firstName           the visitor's first name
    @param lastName            the visitor's last name
    @param balance             starting balance (money available)
    @param learningLevel       starting learning level
    @param visitDuration       visit duration (in minutes or your chosen unit)
    @param preferredLimit      preferred spending limit for this visitor
    @param requiresSupport     true if accessibility support is required, false otherwise
    */
   public Senior(int age, String personID, String firstName, String lastName, double balance, int learningLevel, int visitDuration, double preferredLimit, boolean requiresSupport) {
      super(age, personID, firstName, lastName, balance, learningLevel, visitDuration, preferredLimit);
      this.requiresAccessibilitySupport = requiresSupport;
   }

   //ACCESSOR
   public double getSeniorDiscount() {
      return SENIOR_DISCOUNT;
   }

   public boolean getRequiresAccessibilitySupport() {
      return requiresAccessibilitySupport;
   }

   //MUTATOR
   public void setRequiresAccessibilitySupport(boolean requiresSupport) {
      this.requiresAccessibilitySupport = requiresSupport;
   }

   //OTHER METHODS
   /*
    @return the role string for this Person object
    */
   @Override
   public String getRole() {
      return "SENIOR";
   }

   /*
    @description: calculates the ticket cost for a Senior visitor
                 - if age >= FREE_SENIOR_AGE_MIN, admission is free
                 - otherwise, senior pays adult price with SENIOR_DISCOUNT applied
    @return the admission price for a Senior visitor
    */
   @Override
   public double calculateTicketCost() {
      if (getAge() >= FREE_SENIOR_AGE_MIN) {
         return 0.0;
      }
      return ADULT_BASE_PRICE * (1.0 - SENIOR_DISCOUNT);
   }

   /*
    @description: returns a formatted string summary of this Senior visitor
    @return formatted visitor information including accessibility support status
    */
   @Override
   public String toString() {
      return "PersonID: " + getPersonID() + "\n" +
             "Name: " + getFirstName() + " " + getLastName() + "\n" +
             "Age: " + getAge() + "\n" +
             "Role: " + getRole() + "\n" +
             "Balance: " + getBalance() + "\n" +
             "Learning Level: " + getLearningLevel() + "\n" +
             "Attractions Visited: " + getAttractionsVisited() + "\n" +
             "Num Facts Learned: " + getLearningHistorySize() + "\n" +
             "Preferred Budget Limit: " + getPreferredBudgetLimit() + "\n" +
             "Requires Accessibility Support: " + requiresAccessibilitySupport + "\n";
   }
}


