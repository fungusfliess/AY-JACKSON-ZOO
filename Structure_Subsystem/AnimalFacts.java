/*
   File Name: AnimalFacts.java
   Name: Arianna Liu
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: AnimalFacts represents a collection of facts about a specific animal species.
*/
public class AnimalFacts{

   //FIELDS
   private String[] facts;
   private int numFacts;
   
   //CONSTRUCTOR
   /* @description: declares and initializes AnimalFacts object
         @param facts    array of facts about the animal species
   */
   public AnimalFacts(String[] facts){
      this.facts = facts;
      numFacts = facts.length;
   }
   
   //ACCESSORS
   public String getAnimalFact(int index){
      return facts[index];
   }

   public int getLength(){
      return facts.length;
   }

   /* @description: picks a random fact from the array of facts
        @return: a random fact about the animal species
   */
   public String pickRandomFact(){
      return facts[(int)(Math.random() * numFacts + 1)];
   }
}