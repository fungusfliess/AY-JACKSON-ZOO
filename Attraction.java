
/*
   File Name: Attraction.java
   Name: Jason Liu
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Attraction is an abstract subclass of Structure that represents attractions within the zoo.
                It serves as a base class for specific types of attractions like Park and Maze.
*/

public abstract class Attraction extends Structure {
    // CONSTRUCTOR
    public Attraction (String name, char structureID, int area, int timeBetweenMaintenance, int daysSinceLastMaintenance, Land onProperty) {
        super (name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty);

    }
    // NO ADDITONAL ABSTRACT METHODS ON TOP OF THE ONES IN STRUCTURE

    // demolish is the same for all Attractions, so it can be implemented in the parent class

    /*
    @description: demolishes Structure (flips the boolean, the rest of the demolition is taken care of by the class that calls this method)
    @returns: Boolean indicating success (always true for now, but is designed to be able to indicate if a demolition is not possible)
    */
    @Override
    public boolean demolish () {
        this.setDemolished(true);
        return true;
    }
}