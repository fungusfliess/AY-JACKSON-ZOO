public class Habitat extends Structure{
    private double spaceLeft;
    private int numAnimals;
    private int maxAnimals;
    private LivingCondition climate;
    private Animal[] animals;

    //needs to be fixed after onProperty
    public Habitat(String name, char structureID, int area, int timeBetweenMaintenance, int maxAnimals, int daysSinceLastMaintenance, LivingCondition climate){
        super(name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, null);
    }
}
