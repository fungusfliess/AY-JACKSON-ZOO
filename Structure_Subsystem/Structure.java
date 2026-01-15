import Land_Subsystem.*;
import Person_Subsystem.*;

public abstract class Structure{
   private String name;
   private char structureID;
   private int area;
   private double maintenanceCost;
   private boolean demolished;
   private int timeBetweenMaintenance;
   private int daysSinceLastMaintenance;
   private Land onProperty; 
   
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
   
   //Accessors
   
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
   
   //Mutator
   
   public void setOnProperty(Land onProperty){
      this.onProperty = onProperty;
   }
   
   public void setDemolished(boolean status){
      demolished = status; 
   }

   public abstract double calculateMaintenanceCost();
   
   public abstract void updateVisitorLearning(Visitor toUpdate);
   
   public abstract boolean demolish();
   
   public void passDay(){
      daysSinceLastMaintenance++;
   }
   
   public boolean needsMaintenance(){
      return daysSinceLastMaintenance >= timeBetweenMaintenance; 
   } 
   
   public void maintenance(){
      daysSinceLastMaintenance = 0;
   }
   
   public int compareToSize(Structure structure){
      return this.area - structure.getArea();
   }
   
   public int compareToSinceLastMaintenance(Structure structure){
      return this.daysSinceLastMaintenance - structure.daysSinceLastMaintenance;      
   }
   
   public int compareToNumAnimals(Structure structure){  
      return getNumAnimals() - structure.getNumAnimals(); 
   }
   
   public abstract String saveToString();
}