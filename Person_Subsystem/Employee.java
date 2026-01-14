package Person_Subsystem;
/*
   File Name: Employee.java
   Author: Elizabeth Wang
   Class: ICS4U1-11 
   Date: Jan 7, 2025
   Purpose: Employee represents staff members working at the zoo. It adds job-related fields such as hourly wage, years of experience, and earnings, and 
                supports behavior related to employment. Concrete employee types inherit from this class and implement role-specific details.
*/

//SOMEHOW UPDATE YEARS OF EXPERIENCE BY KEEPING TRACKOF DAYS OF WORKING HERE?? 

public abstract class Employee extends Person {
   //FIELDS 
   public static final int MIN_YEARS_FOR_BENEFITS = 5; 
   private double hourlyWage; 
   private int yearsOfExperience; 
   private double hoursWorked; 
   private double earnings; 
   private boolean benefitsEligible; 
   
   //CONSTRUCTOR
   /*
    @description: declares and initializes Employee object
    @param age               the employee's age (if negative, should be handled by Person)
    @param personID          unique ID for the employee
    @param firstName         the employee's first name
    @param lastName          the employee's last name
    @param hourlyWage        the employee's hourly wage (if negative, it will be made positive)
    @param yearsOfExperience the employee's years of experience (if negative, it will be set to 0)
    */
   public Employee(int age, String personID, String firstName, String lastName, double hourlyWage, int yearsOfExperience){
      super(age, personID, firstName, lastName);
      
      //ensure hourlyWage is non-negative
      if (hourlyWage < 0){
         this.hourlyWage = hourlyWage*-1; 
      } else {
         this.hourlyWage = hourlyWage;       
      }
      
      //ensure yearsOfExperience is valid
      if (yearsOfExperience < 0){
         this.yearsOfExperience = 0; 
      } else {
         this.yearsOfExperience = yearsOfExperience;       
      }
      
      //start day with 0 hours worked and 0 earnings added for that day
      this.hoursWorked = 0.0; 
      this.earnings = 0.0; 

      //determine benefits eligibility
      setBenefitsEligible();
   }
   
   //ACCESSOR
   public double getHourlyWage(){
      return hourlyWage;
   }

   public double getHoursWorked(){
      return hoursWorked;
   }

   public int getYearsOfExperience(){
      return yearsOfExperience;
   }

   public boolean getBenefitsEligible(){
      return benefitsEligible;
   }

   public double getEarnings(){
      return earnings;
   }   
   
   //MUTATOR 
   /*
    @description: updates the employee's total earnings using role-specific rules
    @precondition: subclasses must implement this method (ex: earnings based on hourly wage and hours worked)
    */
   public abstract void setEarnings();

   /*
    @description: updates benefitsEligible based on yearsOfExperience
    */
   public void setBenefitsEligible(){
      if (yearsOfExperience >= MIN_YEARS_FOR_BENEFITS){
         benefitsEligible = true; 
      } else {
         benefitsEligible = false;
      }
   }
   
   //OTHER METHODS 
   /*
    @description: adds worked hours to the employee's daily hoursWorked total
    @param hours number of hours to add (must be > 0)
    @return true if hours were added successfully, false otherwise
    */
   public boolean addHoursWorked(double hours){
      if (hours>0.0){
         hoursWorked += hours; 
         return true; 
      } 
      return false; 
   }
   
   /*
    @description: ends the day for an employee by updating earnings and resetting hoursWorked
    */
   public void passDay(){
      setEarnings(); 
      hoursWorked = 0.0; 
   }

   /*
    @description: adds a positive amount to the employee's total earnings
    @param amount amount to add (must be > 0)
    */
    protected void addToEarnings(double amount) {
      if (amount > 0.0) {
         earnings += amount;
      }
   }
}