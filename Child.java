/*
   File Name: Child.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: 
*/

public class Child extends Visitor{
   public static final double LEARNING_BOOST_FACTOR = 0.20;
   private boolean strollerNeeded;
   private String guardianID;

   public Child(int age, String personID, String firstName, String lastName, double balance, int learningLevel, int visitDuration, boolean strollerNeeded, String guardianID) {
      super(age, personID, firstName, lastName, balance, learningLevel, visitDuration);
      this.strollerNeeded = strollerNeeded;
      this.guardianID = guardianID;
   }

   public double getLearningBoostFactor() {
      return LEARNING_BOOST_FACTOR;
   }

   public boolean getStrollerNeeded() {
      return strollerNeeded;
   }

   public String getGuardianID() {
      return guardianID;
   }

   public void setStrollerNeeded(boolean strollerNeeded) {
      this.strollerNeeded = strollerNeeded;
   }

   public void setGuardianID(String guardianID) {
      this.guardianID = guardianID;
   }

   public String getRole() {
      return "CHILD";
   }

   @Override
   public void addLeavingLevel(int add) {
      if (add <= 0) return;
      int boosted = (int) Math.round(add * (1.0 + LEARNING_BOOST_FACTOR));
      super.addLeavingLevel(boosted);
   }

   @Override
   public double calculateTicketCost() {
      if (getAge() <= FREE_CHILD_AGE_MAX) {
         return 0.0;
      }
      return CHILD_BASE_PRICE;
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
      "Guardian ID: " + guardianID; + "\n";
      "Stroller Needed " + strollerNeeded; + "\n";
   }
}
