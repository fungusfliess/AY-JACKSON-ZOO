/*
   File Name: Adult.java
   Name: Elizabeth Wang
   Class: ICS4U1-11
   Date: Jan 7, 2025
   Description: Adult represents standard visitors who pay the regular admission price. It uses the base visitor fields for balance/learning 
                tracking and returns "ADULT" as the role. Its ticket cost is calculated using the adult base price rules in your system.
*/

import Person_Subsystem.Visitor;

public class Adult extends Visitor{
   private double preferredBudgetLimit; 

   //add adult constructor 
   public double getPreferredBudgetLimit(){
      return preferredBudgetLimit;
   }

   public void setPreferredBudgetLimit(double limit){
      this.preferredBudgetLimit = limit;
   }

   public boolean stayedWithinBudget(){
      return amountSpent<preferredBudgetLimit; 
   }

   public String getRole(){
      return "ADULT";
   }

   public String endVisitorSummary(){
      this.endVisitorSummary(); 
      if (stayedWithinBudget()){
         return "You stayed within your budget.\n";
      } else {
         return "You exceeded your budget.\n";
      }
   }

   public double calculateTicketCost(){
      return ADULT_BASE_PRICE;
   }

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
