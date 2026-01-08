/*
   File Name: ZooKeeper.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: 
*/

public abstract class ZooKeeper extends Employee {
   //fields 
   public static int MAX_CERTIFICATION_LEVEL = 5; 
   public static double CERTIFICATION_BONUS_PERCENTAGE = 0.05;
   
   private int certificationLevel; 
   private int dailyTasksCompleted;  
   
   public ZooKeeper(int age, String personID, String firstName, String lastName, double hourlyWage, int yearsOfExperience, int certificationLevel){
      super(age, personID, firstName, lastName, hourlyWage, yearsOfExperience);
      this.certificationLevel = certificationLevel;
      if (certificationLevel<1){
         this.certificationLevel = 1; 
      }
      dailyTasksCompleted = 0; 
   }   
   
   public int getCertificationLevel(){return certificationLevel;}
   public int getDailyTasksCompleted(){return dailyTasksCompleted;}
   
   public void setEarnings(){
      earnings = (hourlyWage*hoursWorked)*(1 + CERTIFICATION_BONUS_PERCENTAGE);
   }
   
   public void setCertificationLevel(int c){
      certificationLevel = min (c, MAX_CERTIFICATION_LEVEL);
   }
}