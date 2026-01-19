/*
   File Name: ZooKeeper.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: ZooKeeper is a concrete subclass of Employee that represents staff who care for animals and work with
                exhibits. It tracks certification level and daily tasks completed, and calculates earnings using a
                certification-based bonus. ZooKeeper returns the role identifier "ZOOKEEPER" and provides animal-care actions.
*/
 
public class ZooKeeper extends Employee {
    //CONSTANTS 
    public static final int MAX_CERTIFICATION_LEVEL = 5; 
    public static final int MIN_CERTIFICATION_LEVEL = 1; 
    public static final double CERTIFICATION_BONUS_PERCENTAGE = 0.05;
   
    //FIELDS 
    private int certificationLevel; 
    private int dailyTasksCompleted;  
    
    //CONSTRUCTOR
    /*
     @description: declares and initializes ZooKeeper object
     @param age                the employee's age
     @param personID           unique ID for the employee
     @param firstName          the employee's first name
     @param lastName           the employee's last name
     @param hourlyWage         the employee's hourly wage
     @param yearsOfExperience  the employee's years of experience
     @param certificationLevel certification level (clamped to range 1..MAX_CERTIFICATION_LEVEL)
    */
    public ZooKeeper(int age, String personID, String firstName, String lastName, double hourlyWage, int yearsOfExperience, int certificationLevel){
        super(age, personID, firstName, lastName, hourlyWage, yearsOfExperience);
        this.certificationLevel = certificationLevel;

        //ensure certification level is within valid range
        if (certificationLevel<MIN_CERTIFICATION_LEVEL){
            this.certificationLevel = MIN_CERTIFICATION_LEVEL; 
        } else if (certificationLevel>MAX_CERTIFICATION_LEVEL){
            this.certificationLevel = MAX_CERTIFICATION_LEVEL; 
        } else {
            this.certificationLevel = certificationLevel; 
        }
        dailyTasksCompleted = 0; 
    }   
    
    //ACCESSOR
    public int getCertificationLevel(){
        return certificationLevel;
    }

    public int getDailyTasksCompleted(){
        return dailyTasksCompleted;
    }
    
    //MUTATOR 
    /*
     @description: updates the employee's total earnings for the day using ZooKeeper pay rules
     @note: includes a bonus multiplier based on certification level
     */
    @Override
    public void setEarnings() {
        double multiplier = 1.0 + (getCertificationLevel() * CERTIFICATION_BONUS_PERCENTAGE);
        double dayPay = getHourlyWage() * getHoursWorked() * multiplier;
        addToEarnings(dayPay);
    }

    /*
     @description: sets the certification level, clamped to MAX_CERTIFICATION_LEVEL (and minimum 1)
     @param c new certification level
     */
    public void setCertificationLevel(int c){
        certificationLevel = Math.min(c, MAX_CERTIFICATION_LEVEL);
    }

    //OTHER METHODS 
    /*
     @return the role string for this Person object
     */
    @Override
    public String getRole(){
        return "ZOOKEEPER"; 
    }

    /*
     @description: feeds an animal and increments dailyTasksCompleted if successful
     @param a the Animal to feed
     @return true if the animal was fed, false otherwise
     */
    public boolean feedAnimal(Animal a, String food, int amount){
        if (a==null){return false;}             
        a.eat(food, amount); 
        dailyTasksCompleted++; 
        return true; 
    }

    /*
     @description: plays with an animal and increments dailyTasksCompleted if successful
     @param a the Animal to interact with
     @return true if the interaction happened, false otherwise
     */
    public boolean playWithAnimal(Animal a){
        if (a==null){return false;}              
        a.interact("play"); 
        dailyTasksCompleted++;
        return true;
    }

    /*
     @description: cleans an animal (calls Animal cleaning method if it exists) and increments dailyTasksCompleted
     @param a the Animal to clean
     @return true if the animal was cleaned, false otherwise
     */
    public boolean cleanAnimal(Animal a) {
        if (a == null) return false;
        a.interact("clean");

        dailyTasksCompleted++;
        return true;
    }
    /*
     @description: returns a formatted string summary of this ZooKeeper employee
     @return formatted employee information
     */
    @Override
    public String toString() {
        return "PersonID: " + getPersonID() + "\n" +
                "Name: " + getFirstName() + " " + getLastName() + "\n" +
                "Age: " + getAge() + "\n" +
                "Role: " + getRole() + "\n" +
                "Certification Level: " + certificationLevel + "\n" + 
                "Tasks Completed: " + dailyTasksCompleted + "\n" + 
                "Earnings: " + getEarnings() + "\n";
    }

    /*
     @description: ends the day for ZooKeeper by calculating earnings, resetting hours worked (in Employee),
                   and resetting ZooKeeper daily counters
    */
    @Override
    public void passDay(){
        super.passDay(); 
        dailyTasksCompleted =0; 
    }
}