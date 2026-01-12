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
   
   public Coord find(){
      return onProperty.find(structureID);     
   }
   
   public int compareToSize(Structure structure){
      return this.size - structure.getArea();
   }
   
   public int compareToSinceLastMaintenance(Structure structure){
      return this.daysSinceLastMaintenance - structure.daysSinceLastMaintenance;      
   }
   
   public int compareToNumAnimals(Structure structure){  
      if(this instanceof Habitat && structure instanceof Habitat){
         return ((Habitat)this).getNumAnimals() - ((Habitat)structure).getNumAnimals();
      }else if(this instanceof Habitat){
         return ((Habitat)this).getNumAnimals(); 
      }else if(strcture instanceof Habitat){
         return -1 * ((Habitat)structure).getNumAnimals();
      }else{
         return 0;
      }
   }
   
   public abstract String saveToString();
}