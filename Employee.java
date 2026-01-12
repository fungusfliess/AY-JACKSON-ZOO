/*
   File Name: Employee.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: 
*/

public abstract class Employee extends Person {
   //fields 
   //SOMEHOW UPDATE YEARS OF EXPERIENCE BY KEEPING TRACKOF DAYS OF WORKING HERE?? 
   public static final int MIN_YEARS_FOR_BENEFITS = 5; 
   private double hourlyWage; 
   private int yearsOfExperience; 
   private double hoursWorked; 
   private double earnings; 
   private boolean benefitsEligible; 
   
   public Employee(int age, String personID, String firstName, String lastName, double hourlyWage, int yearsOfExperience){
      super(age, personID, firstName, lastName);
      
      if (hourlyWage < 0){
         this.hourlyWage = hourlyWage*-1; 
      } else {
         this.hourlyWage = hourlyWage;       
      }
      
      if (yearsOfExperience < 0){
         this.yearsOfExperience = 0; 
      } else {
         this.yearsOfExperience = yearsOfExperience;       
      }
      
      this.hoursWorked = 0.0; 
      setBenefitsEligible();
   }
   
   public double getHourlyWage(){return hourlyWage;}
   public double getHoursWorked(){return hoursWorked;}
   public int getYearsOfExperience(){return yearsOfExperience;}
   public boolean getBenefitsEligible(){return benefitsEligible;}
   public double getEarnings(){return earnings;}   
   
   public abstract void setEarnings();
   public void setBenefitsEligible(){
      if (yearsOfExperience>=MIN_YEARS_FOR_BENEFITS){
         benefitsEligible = true; 
      } else {
         benefitsEligible = false;
      }
   }
   
   public boolean addHoursWorked(double hours){
      if (hours>0.0){
         hoursWorked += hours; 
         return true; 
      } 
      return false; 
   }
   
   public void passDay(){
      setEarnings(); 
      hoursWorked = 0.0; 
   }
   
}