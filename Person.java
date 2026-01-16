/*
   File Name: Person.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Person is the abstract base class for everyone in the zoo system. It stores shared identity
                information (personID, first/last name, age) and an active status flag. Subclasses must
                implement getRole() and may override methods like toString() and saveToString().
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
   
   //OTHER METHODS 
   /*
    @return the role string for this Person object (ex: "ZOOKEEPER", "SHOPSTAFF", "ADULT", "CHILD", "SENIOR")
    */ 
   public abstract String getRole();

   /*
   @description: returns this Person in file format so it can be saved and reloaded later
   @return a string formatted for writing to person.txt
   */
   public abstract String saveToString();

}