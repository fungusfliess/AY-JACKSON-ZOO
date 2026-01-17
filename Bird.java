public abstract class Bird extends Animal {

    protected boolean canFly;
    protected int flyingAge;
    protected boolean hasNest;

    // Description: constructor for birds (template-based)
    public Bird(Animal parent) {
        super(parent);
        this.hasNest = false;
    }

    // Description: full constructor
    public Bird(char habitatId, String name, String specie, String preferedInteraction, String gender,
                int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, specie, preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);

        this.hasNest = false;
    }

    // =========================
    // SHARED BIRD METHODS
    // =========================

    public Egg reproduce() {
        if (canReproduce()) {

            Egg egg = new Egg(this);
            return egg;
        }
        return null;
    }

    @Override
    public boolean canReproduce() {
        return super.canReproduce() && hasNest;
    }

    public void buildNest() {
        this.hasNest = true;
    }
    
    @Override
    public String saveToString() {
        return super.saveToString() + "\n" + hasNest;
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nCan Fly: " + this.canFly +
               "\nFlying Age: " + this.flyingAge +
               "\nHas Nest: " + this.hasNest;
    }
}
