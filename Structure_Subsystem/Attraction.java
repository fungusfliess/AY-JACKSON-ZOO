package Structure_Subsystem;
import Land_Subsystem.*;

public abstract class Attraction extends Structure {
    public Attraction (String name, char structureID, int area, int timeBetweenMaintenance, int daysSinceLastMaintenance, Land onProperty) {
        super (name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty);

    }

    public abstract double calculateMaintenanceCost ();

    public abstract void updateVisitorLearning (Visitor guest);

    public boolean demolish () {
        this.setDemolished(true);
        return true;
    }
}