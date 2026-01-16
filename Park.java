/*
    File Name: Park.java
    Name: Jason Liu
    Class: ICS4U1-23
    Date: Jan 7, 2025
    Description: Park is a subclass of Attraction that represents a park within the zoo.
*/

public class Park extends Attraction {

    //CONSTANTS
    public static final int LEARNING_PER_VISIT = 20;
    public static final double MAINTENANCE_RATE_PER_UNIT = 4;

    /*
    @description: Constructor for Park class
    @param: Self explanatory
    */
    public Park (String name, char structureID, int area, int timeBetweenMaintenance, int daysSinceLastMaintenance, Land onProperty) {
        super (name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty);
    }

    /*
    @description: calculates maintenance cost of the Park based on area
    @returns: double representing maintenance cost
    */
    @Override
    public double calculateMaintenanceCost() {
        return MAINTENANCE_RATE_PER_UNIT * this.getArea();
    }
    @Override
    public void updateVisitorLearning(Visitor toUpdate) {
        // COME_BACK_HERE waiting on how
    }

    // LOAD AND SAVE
    /*
    @description: loads a Park from a string
    @param input: the string to load the Park from, onProperty: the land on which this park is located
    @returns: Park object
    */
    public static Park loadFromString (String input, Land onProperty) {
        // turning String into array of its lines
        String[] fields = input.split("\n");
        
        // parse each line into respective parameters
        int idx = 0; // index that can count through the array of fields.
        
        // declaring variables to load in next.
        String name;
        char structureID;
        int area, timeBetweenMaintenance, daysSinceLastMaintenance;

        name = fields[idx];
        idx++;
        structureID = fields[idx].charAt(0);
        idx++;
        area = Integer.parseInt(fields[idx]);
        idx++;
        timeBetweenMaintenance = Integer.parseInt(fields[idx]);
        idx++;
        daysSinceLastMaintenance = Integer.parseInt(fields[idx]);
        
        Park newPark = new Park (name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty); 
        return newPark;

   }

   /*
   @description: saves this Park to a string
   @returns: formatted string representation of this Park, to be written into a file
   */
  @Override
   public String saveToString () {
        String sum = "";
        sum += "Park" + "\n"; // class type, reference for reading in
        sum += this.getName() +"\n";
        sum += this.getStructureID() + "\n";
        sum += this.getArea() + "\n";
        sum += this.getTimeBetweenMaintenance() + "\n";
        sum += this.getDaysSinceLastMaintenance() + "\n";
        // COME_BACK_HERE add extra empty line or no?? find out later when we integrate all subsystems together.

        return sum;
   }


    /*
    @description: returns a user-friendly string representation of Park
    @returns: formatted string with Park details
    */
   public String toString () {
      return "Park Name: " + getName() + "\nStructure ID: " + getStructureID() + "\nArea: " + getArea() + "\nMaintenance Cost: " + getMaintenanceCost() + "\nTime Between Maintenance: " + getTimeBetweenMaintenance() + "\nDays Since Last Maintenance: " + getDaysSinceLastMaintenance();
   }
}