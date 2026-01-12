/*
   File Name: Senior.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: 
*/

public class Senior extends Adult{
   public static final double SENIOR_DISCOUNT = 0.20; 
   private boolean requiresAccessibilitySupport; 

   public Senior(int age, String personID, String firstName, String lastName, double balance, int learningLevel, int visitDuration, double preferredLimit, boolean requiresSupport) {
      super(age, personID, firstName, lastName, balance, learningLevel, visitDuration, preferredLimit);
      this.requiresAccessibilitySupport = requiresSupport;
   }

   public double getSeniorDiscount() {
      return SENIOR_DISCOUNT;
   }

   public boolean getRequiresAccessibilitySupport() {
      return requiresAccessibilitySupport;
   }

   // setter with param (recommended)
   public void setRequiresAccessibilitySupport(boolean requiresSupport) {
      this.requiresAccessibilitySupport = requiresSupport;
   }

   public String getRole() {
      return "SENIOR";
   }

   @Override
   public double calculateTicketCost() {
      if (getAge() >= FREE_SENIOR_AGE_MIN) {
         return 0.0;
      }
      return ADULT_BASE_PRICE * (1.0 - SENIOR_DISCOUNT);
   }

   @Override
   public String toString() {
      return "PersonID: " + personID + "\n" + 
      "Name: " + firstName + " " + lastName + "\n" + 
      "Age: " + age + "\n" + 
      "Role: " + this.getRole() + "\n" + 
      "Balance: " + balance + "\n" + 
      "Learning Level: " + learningLevel + "\n" + 
      "Attractions Visited: " + attractionsVisited + "\n" +
      "Num Facts Learned: " + learningHistorySize + "\n" +
      "Preferred Budget Limit: " + preferredBudgetLimit + "\n"; 
      "Requires Accessibility Support: " + requiresAccessibilitySupport; + "\n";
   }
}


