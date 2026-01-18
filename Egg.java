public class Egg {

    // =========================
    // CONSTANTS
    // =========================
    public static final int SHARK_HATCH_TIME = 90;
    public static final int SUNFISH_HATCH_TIME = 60;
    public static final int SNAKE_HATCH_TIME = 40;
    public static final int CROCODILE_HATCH_TIME = 80;
    public static final int FROG_HATCH_TIME = 30;
    public static final int AXOLOTL_HATCH_TIME = 25;
    public static final int EAGLE_HATCH_TIME = 35;
    public static final int COCKATOO_HATCH_TIME = 45;

    // =========================
    // FIELDS
    // =========================
    private int hatchTime;
    private boolean hatched;
    private Animal parent;

    // =========================
    // CONSTRUCTOR
    // =========================
    public Egg(Animal parent) {
        this.parent = parent;
        this.hatched = false;
        this.hatchTime = getInitialHatchTime(parent);
    }

    // =========================
    // GETTERS
    // =========================
    public int getHatchTime() {
        return hatchTime;
    }

    public Animal getParent() {
        return parent;
    }

    public boolean hasHatched() {
        return hatched;
    }

    // =========================
    // SETTERS
    // =========================
    public void setHatchTime(int hatchTime) {
        this.hatchTime = hatchTime;
    }

    public void setParent(Animal parent) {
        this.parent = parent;
    }

    // =========================
    // TIME METHODS
    // =========================
    public void passDay() {
        hatchTime--;
    }

    public boolean readyToHatch() {
        return hatchTime <= 0;
    }

    // =========================
    // HATCH METHOD
    // =========================
    public Animal hatch(String name) {

        if (hatched) {
            System.out.println("This egg has already hatched.");
            return null;
        }

        if (!readyToHatch()) {
            System.out.println("The egg is not ready to hatch yet.");
            return null;
        }

        Animal baby = null;

        if (parent instanceof Shark) {
            baby = new Shark(parent);
        }
        else if (parent instanceof Sunfish) {
            baby = new Sunfish(parent);
        }
        else if (parent instanceof Snake) {
            baby = new Snake(parent);
        }
        else if (parent instanceof Crocodile) {
            baby = new Crocodile(parent);
        }
        else if (parent instanceof Frog) {
            baby = new Frog(parent);
        }
        else if (parent instanceof Axolotl) {
            baby = new Axolotl(parent);
        }
        else if (parent instanceof Eagle) {
            baby = new Eagle(parent);
        }
        else if (parent instanceof Cockatoo) {
            baby = new Cockatoo(parent);
        }

        if (baby == null) {
            System.out.println("Unknown species. Egg failed to hatch.");
            return null;
        }

        baby.setName(name);
        baby.setGender(Math.random() < 0.5 ? "Male" : "Female");
        hatched = true;

        System.out.println(name + " has been born!");
        return baby;
    }

    // =========================
    // HELPER
    // =========================
    private int getInitialHatchTime(Animal parent) {

        if (parent instanceof Shark) return SHARK_HATCH_TIME;
        if (parent instanceof Sunfish) return SUNFISH_HATCH_TIME;
        if (parent instanceof Snake) return SNAKE_HATCH_TIME;
        if (parent instanceof Crocodile) return CROCODILE_HATCH_TIME;
        if (parent instanceof Frog) return FROG_HATCH_TIME;
        if (parent instanceof Axolotl) return AXOLOTL_HATCH_TIME;
        if (parent instanceof Eagle) return EAGLE_HATCH_TIME;
        if (parent instanceof Cockatoo) return COCKATOO_HATCH_TIME;

        return 0;
    }

    // =========================
    // SAVE
    // =========================
    public String saveToString() {

        String group = "";

        if (parent instanceof Shark) group = "Shark";
        else if (parent instanceof Sunfish) group = "Sunfish";
        else if (parent instanceof Snake) group = "Snake";
        else if (parent instanceof Crocodile) group = "Crocodile";
        else if (parent instanceof Frog) group = "Frog";
        else if (parent instanceof Axolotl) group = "Axolotl";
        else if (parent instanceof Eagle) group = "Eagle";
        else if (parent instanceof Cockatoo) group = "Cockatoo";

        return parent.getName() + "\n" +
               group + "\n" +
               hatchTime;
    }

    // =========================
    // STRING
    // =========================
    public String toString() {
        return "Egg of " + parent.getClass().getSimpleName() + "\n" +
               "Hatch Time Remaining: " + hatchTime + " days.";
    }
}
