/*
   File Name: Child.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Child is a concrete subclass of Visitor that represents younger visitors. It may receive free
                or discounted admission, applies a learning boost when gaining learning level, and stores extra
                information such as strollerNeeded and guardianID. Child returns the role identifier "CHILD".
*/

public class Child extends Visitor{
   //CONSTANTS 
   public static final double LEARNING_BOOST_FACTOR = 0.20;

   //FIELDS
   private boolean strollerNeeded;
   private String guardianID;

   //CONSTRUCTOR
   /*
    @description: declares and initializes Child visitor object
    @param age           the visitor's age
    @param personID      unique ID for the visitor
    @param firstName     the visitor's first name
    @param lastName      the visitor's last name
    @param balance       starting balance (money available)
    @param learningLevel starting learning level
    @param visitDuration visit duration (in minutes or your chosen unit)
    @param strollerNeeded true if a stroller is needed, false otherwise
    @param guardianID    personID of the guardian responsible for this child
    */
   public Child(int age, String personID, String firstName, String lastName, double balance, int learningLevel, int visitDuration, boolean strollerNeeded, String guardianID) {
      super(age, personID, firstName, lastName, balance, learningLevel, visitDuration);
      this.strollerNeeded = strollerNeeded;
      this.guardianID = guardianID;
   }

   //ACCESSOR
   public double getLearningBoostFactor() {
      return LEARNING_BOOST_FACTOR;
   }

   public boolean getStrollerNeeded() {
      return strollerNeeded;
   }

   public String getGuardianID() {
      return guardianID;
   }

   //MUTATOR
   public void setStrollerNeeded(boolean strollerNeeded) {
      this.strollerNeeded = strollerNeeded;
   }

   public void setGuardianID(String guardianID) {
      this.guardianID = guardianID;
   }

   //OTHER METHODS 
   /*
    @return the role string for this Person object
    */
   @Override
   public String getRole() {
      return "CHILD";
   }

   /*
    @description: overrides Visitor learning level increase by applying a learning boost
    @param add the base amount to increase learning level by (must be > 0)
    */
   @Override
   public void addLeavingLevel(int add) {
      if (add <= 0) return;
      int boosted = (int) Math.round(add * (1.0 + LEARNING_BOOST_FACTOR));
      super.addLeavingLevel(boosted);
   }

   /*
    @description: calculates the ticket cost for a Child visitor
                 - if age <= FREE_CHILD_AGE_MAX, admission is free
                 - otherwise, child pays CHILD_BASE_PRICE
    @return the admission price for a Child visitor
    */
   @Override
   public double calculateTicketCost() {
      if (getAge() <= FREE_CHILD_AGE_MAX) {
         return 0.0;
      }
      return CHILD_BASE_PRICE;
   }

   /*
    @description: returns a formatted string summary of this Child visitor
    @return formatted visitor information including guardianID and stroller status
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
             "Guardian ID: " + guardianID + "\n" +
             "Stroller Needed: " + strollerNeeded + "\n";
   }

   /*
   @description: returns this Child in file format (includes accessibility support field)
   @return a string formatted for writing to person.txt
   */
   @Override
   public String saveToString() {
      return super.saveToString() +
            strollerNeeded + "\n" + 
            guardianID + "\n";
   }
}
