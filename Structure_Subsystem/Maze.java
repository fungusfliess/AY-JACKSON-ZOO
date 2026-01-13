package Structure_Subsystem;
import Land_Subsystem.*;

public Maze extends Attraction {
    public Maze (String name, char structureID, int area, int timeBetweenMaintenance, int daysSinceLastMaintenance, Land onProperty) {
        super (name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty);

    }
}