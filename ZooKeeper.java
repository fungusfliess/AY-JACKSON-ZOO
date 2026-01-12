/*
   File Name: ZooKeeper.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: 
*/

import Animal_Subsystem.Animal;

public abstract class ZooKeeper extends Employee {
   //fields 
    public static final int MAX_CERTIFICATION_LEVEL = 5; 
    public static final double CERTIFICATION_BONUS_PERCENTAGE = 0.05;
   
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
        certificationLevel = min(c, MAX_CERTIFICATION_LEVEL);
    }

    public String getRole(){
        return "ZOOKEEPER"; 
    }

    public boolean feedAnimal(Animal a){
        if (a==null){return false;}             //OR if animal is NOT avaible, has passed away. etc 
        a.eat(); 
        dailyTasksCompleted++; 
        return true; 
    }

    public boolean playWithAnimal(Animal a){
        if (a==null){return false;}              
        a.interact("play"); 
        dailyTasksCompleted++;
        return true;
    }

    public boolean cleanAnimal(Animal a){
        //RESTE OR INCREASE ANIMAL CLEANINESS VALUE 
        dailyTasksCompleted++; 
    }

    public String toString(){
        return "PersonID: " + personID + "\n" + 
                "Name: " + firstName + " " + lastName + "\n" + 
                "Age: " + age + "\n" + 
                "Role: " + this.getRole() + "\n" + 
                "Certification Level: " + certificationLevel + "\n" + 
                "Tasks Completed: " + dailyTasksCompleted + "\n" + 
                "Earnings: " + earnings + "\n"; 
    }

    public void passDay(){
        super.passDay(); 
        dailyTasksCompleted =0; 
    }
}