package Person_Subsystem;
/*
   File Name: Senior.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Senior represents older visitors who may receive a senior discount and can require accessibility support. It stores a boolean 
                like requiresAccessibilitySupport and uses senior pricing rules (including free admission if your program has a free-senior age 
                threshold). It returns "SENIOR" as the role and includes accessibility status in its summary output.
*/

import Adult;
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


