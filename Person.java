/*
   File Name: Person.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: 
*/

public abstract class Person {
   //fields 
   private int age; 
   private String personID; 
   private String firstName; 
   private String lastName; 
   private boolean isActive; 
   
   public Person(int age, String personID, String firstName, String lastName){
      this.age = age; 
      this.personID = personID; 
      this.firstName = firstName;
      this.lastName = lastName; 
      isActive = true; 
   }
   
   public String getPersonID(){return personID;}
   public int getAge(){return age;}
   public String getFirstName(){return firstName;}
   public String getLastName(){return lastName;}
   public boolean getIsActive(){return isActive;}
   
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
   
   public abstract String getRole();
   
   public abstract String toString();
   
}