public abstract class Reptile extends Animal {

    protected int timeToShed;

    // Description: constructor for reptiles (template-based)
    public Reptile(Animal parent) {
        super(parent);
    }

    // Description: full constructor
    public Reptile(char habitatId, String name, String specie, String preferedInteraction, String gender,
                   int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, specie, preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);
    }

    // =========================
    // SHARED REPTILE METHODS
    // =========================

    // Description: reptiles reproduce via eggs
    public Egg reproduce() {
        if (canReproduce()) {

            return new Egg(this);
        }
        return null;
    }

    public void shedSkin() {
        if (timeToShed <= 0) {
            System.out.println(getName() + " the " + getSpecie() + " has shed its skin!");
            timeToShed = getInitialTimeToShed();
            setCleanliness(MAX_STAT);
        } else {
            System.out.println(getName() + " the " + getSpecie() + " is not ready to shed its skin yet.");
        }
    }

    @Override
    public String saveToString() {
        return super.saveToString() + "\n" + timeToShed;
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nTime to Shed: " + timeToShed + " days";
    }

    // =========================
    // SPECIES HOOKS
    // =========================

    public abstract int getInitialTimeToShed();
}
