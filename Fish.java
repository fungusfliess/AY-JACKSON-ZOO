package Animal_Subsystem;

import Structure_Subsystem.*;

public class Fish extends Animal{
    // Description: constructor for fish
    public Fish(String name, String specie, String preferedInteraction, String gender,
                int happiness, int cleanliness, int hunger, int totalDailyInteractions, int age) {
        super(name) = name;
    }

    // METHODS

    // Description: abstract method that formats all information of the animal
    public String toString() {

    }

    // Description: abstract method that creates a new animal of the same type as its parent.
    //  This method is different for animals that produce offspring as eggs.
    //  Returns null if the animal does not have the requirements to reproduce.
    public Animal reproduce(Habitat habitat) {

    }

    // Description: abstract helper method that updates the age of the animal
    public void updateAge() {

    }
}
