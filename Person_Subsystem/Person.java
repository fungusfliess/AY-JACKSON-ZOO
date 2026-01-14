package Person_Subsystem;
/*
   File Name: Person.java
   Author: Elizabeth Wang
   Class: ICS4U1-11 
   Date: Jan 7, 2025
   Purpose: Person is the base class for everyone in the zoo system. It stores shared identity info like personID, first/last name, age, and an “active” status. 
            It provides common getters/setters and a deactivate() method so any person can be removed from the current zoo without deleting their record.
*/

public abstract class Person {
   //FIELDS 
   private int age; 
   private String personID; 
   private String firstName; 
   private String lastName; 
   private boolean isActive; 
   
   //CONSTRUCTOR 
   /*
    @description: declares and initializes Person object
    @param age       the person's age (if negative, it will be set to 0)
    @param personID  unique ID for the person
    @param firstName the person's first name
    @param lastName  the person's last name
    */ 
   public Person(int age, String personID, String firstName, String lastName){
      this.age = age; 
      this.personID = personID; 
      this.firstName = firstName;
      this.lastName = lastName; 
      isActive = true; 
   }
   
   //ACCESSOR 
   public String getPersonID(){
      return personID;
   }

   public int getAge(){
      return age;
   }

   public String getFirstName(){
      return firstName;
   }

   public String getLastName(){
      return lastName;

   }

   public boolean getIsActive(){
      return isActive;

   }
   
   //MUTATOR 
   public void setAge(int age){
      if (age>=0){
         this.age = age; 
      }
   }

   public void activate(){
      isActive = true; 
   }
   
   public void deactivate(){
      isActive = false; 
   }
   
   /*
    @return the role string for this Person object (ex: "ZOOKEEPER", "SHOPSTAFF", "ADULT", "CHILD", "SENIOR")
    */ 
   public abstract String getRole();
}