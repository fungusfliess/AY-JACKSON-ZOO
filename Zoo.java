import Animal_Subsystem.Animal;
import Animal_Subsystem.LivingCondition;
import Structure_Subsystem.Habitat;

public class Zoo {
    int numberOfAnimals;
    private Animal[] animals;
    private LivingCondition[] livingConditions;
    private Habitat[] habitats;

    public Zoo(int numberOfAnimals) {
        this.numberOfAnimals = numberOfAnimals;
    }
    public Zoo(String file) {
        
        // Load animals, habitats, and living conditions from files
    }

    public String toString() {
        return "This zoo has " + numberOfAnimals + " animals.";
    }
}
