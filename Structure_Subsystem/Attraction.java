package Structure_Subsystem;
import Land_Subsystem.*;

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
    public boolean demolish () {
        this.setDemolished(true);
        return true;
    }
}