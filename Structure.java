/*
   File Name: Structure.java
   Name: Arianna Liu
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Structure represents a building or facility within the zoo. It includes fields for name, ID, area, maintenance cost, and other properties related to the structure's condition and usage.
*/

public abstract class Structure{

   //FIELDS
   private String name;
   private char structureID;
   private int area;
   private double maintenanceCost;
   private boolean demolished;
   private int timeBetweenMaintenance;
   private int daysSinceLastMaintenance;
   private Land onProperty; 

   //CONSTRUCTOR
   /*
     @description: declares and initializes Structure object
     @param name                the name of the structure
     @param structureID         unique ID for the structure
     @param area                the area of the structure
     @param timeBetweenMaintenance  the time between maintenance cycles
     @param daysSinceLastMaintenance  the number of days since last maintenance
     @param onProperty          the land on which this structure is located
    */
   
   public Structure(String name, char structureID, int area, int timeBetweenMaintenance, int daysSinceLastMaintenance, Land onProperty){
      this.name = name;
      this.structureID = structureID;
      this.area = area;
      this.maintenanceCost = calculateMaintenanceCost();
      this.demolished = false;
      this.timeBetweenMaintenance = timeBetweenMaintenance;
      this.daysSinceLastMaintenance = daysSinceLastMaintenance; 
      this.onProperty = onProperty;
   }
   
   //ACCESSORS

   public String getName(){
      return name;
   }
   public char getStructureID(){
      return structureID;
   }
   public int getArea(){
      return area;
   }
   public double getMaintenanceCost(){
      return maintenanceCost;
   }
   public int getTimeBetweenMaintenance(){
      return timeBetweenMaintenance;
   }
   public int getDaysSinceLastMaintenance(){
      return daysSinceLastMaintenance; 
   }
   public int getNumAnimals(){
      return 0;
   }
   public boolean isDemolished(){
      return demolished; 
   }
   public Land getOnProperty(){
      return onProperty;
   }
   
   //MUTATORS
   
   public void setOnProperty(Land onProperty){
      this.onProperty = onProperty;
   }
   
   public void setDemolished(boolean status){
      demolished = status; 
   }

   public void setArea (int in) {
      this.area = in;
   }

   //OTHER METHODS


   /*
     @description: calculates the maintenance cost for this structure
     @note: implemented by subclasses
     @return the maintenance cost
   */
   public abstract double calculateMaintenanceCost();


   /* 
     @description: updates the visitor's learning about the structure
     @param toUpdate: the visitor to update
   */
   public abstract void updateVisitorLearning(Visitor toUpdate);

   /*
     @description: determines if the structure can be demolished, demolishes structure if possible
     @return true if the structure can be demolished, false otherwise
   */
   public abstract boolean demolish();

   /*
     @description: increments the days since last maintenance by 1
   */
   public void passDay(){
      daysSinceLastMaintenance++;
   }

   /*
     @description: determines if the structure needs maintenance
     @return true if the structure needs maintenance, false otherwise
   */
   public boolean needsMaintenance(){
      return daysSinceLastMaintenance >= timeBetweenMaintenance; 
   } 

   /*
     @description: performs maintenance on the structure, resetting daysSinceLastMaintenance to 0
   */
   public void maintenance(){
      daysSinceLastMaintenance = 0;
   }

   /* 
     @description: compares the size of this structure to another structure
     @param structure the other structure to compare to
     @return the difference in area between this structure and the other structure
   */
   public int compareToSize(Structure structure){
      return this.area - structure.getArea();
   }

   /* 
     @description: compares the days since last maintenance of this structure to another structure
     @param structure the other structure to compare to
     @return the difference in days since last maintenance between this structure and the other structure
   */
   public int compareToSinceLastMaintenance(Structure structure){
      return this.daysSinceLastMaintenance - structure.daysSinceLastMaintenance;      
   }

   /* 
     @description: compares the number of animals housed in this structure to another structure
     @param structure the other structure to compare to
     @return the difference in number of animals between this structure and the other structure
   */  
   public int compareToNumAnimals(Structure structure){  
      return getNumAnimals() - structure.getNumAnimals(); 
   }

   public int compareToID (Structure structure) {
      return this.getStructureID() - structure.getStructureID();
   }

   /* 
     @description: saves the structure to a string
      @return the structure as a string
   */
   public abstract String saveToString();

   public abstract String toString();
}