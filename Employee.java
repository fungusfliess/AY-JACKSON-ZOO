/*
   File Name: Employee.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Employee is an abstract subclass of Person that represents all zoo staff members. It stores
                job-related data such as hourly wage, years of experience, hours worked, total earnings, and
                benefits eligibility. Concrete subclasses (ex: ZooKeeper, ShopStaff) implement setEarnings()
                and provide role-specific behavior.
*/ 

public abstract class Employee extends Person {
   //FIELDS 
   public static final int MIN_YEARS_FOR_BENEFITS = 5; 
   public static final int DAYS_PER_YEAR = 365; 
   private double hourlyWage; 
   private int yearsOfExperience; 
   private int daysWorked; 
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
      
      //start with 0 days worked
      daysWorked = 0; 

      //start day with 0 hours worked and 0 earnings added for that day
      this.hoursWorked = 0.0; 
      this.earnings = 0.0; 

      //determine benefits eligibility
      setBenefitsEligible();
   }

   public Employee(int age, String personID, String firstName, String lastName,
      double hourlyWage, int yearsOfExperience,
      double hoursWorked, double earnings) {

      super(age, personID, firstName, lastName);

      this.hourlyWage = Math.abs(hourlyWage);
      this.yearsOfExperience = Math.max(0, yearsOfExperience);

      this.hoursWorked = Math.max(0.0, hoursWorked);
      this.earnings = Math.max(0.0, earnings);

      this.daysWorked = 0; 
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

   public int getDaysWorked(){
      return daysWorked;
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
   /**
    * Compares this employee to another employee by total earnings (highest first).
    * @param other the Employee to compare to
    * @return negative if this should come before other
    */
   public double compareToByEarnings(Employee other) {
      return other.getEarnings()-this.getEarnings();
   }

   /**
    * Compares this employee to another employee by years of experience (highest first).
    * @param other the Employee to compare to
    * @return negative if this should come before other
    */
   public int compareToByExperience(Employee other) {
      return other.getYearsOfExperience() - this.getYearsOfExperience();
   }

   /**
    * Compares this employee to another employee by hourly wage (highest first).
    * @param other the Employee to compare to
    * @return negative if this should come before other
    */
   public double compareToByWage(Employee other) {
      return other.getHourlyWage()- this.getHourlyWage();
   }

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
    @description: adds a positive amount to the employee's total earnings
    @param amount amount to add (must be > 0)
    @return a boolean to indicate whether adding was successful or not
    */
    public boolean addToEarnings(double amount) {
      if (amount > 0.0) {
         earnings += amount;
         return true;
      }
      return false; 
   }
   
   /*
    @description: ends the day for an employee by updating earnings and resetting hoursWorked
    */
   public void passDay(){
      daysWorked++;

      // Every 365 days worked -> +1 year of experience
      if (daysWorked == DAYS_PER_YEAR) {
         yearsOfExperience++;
         daysWorked = 0; 
         setBenefitsEligible(); 
      }
      setEarnings(); 
   }
}