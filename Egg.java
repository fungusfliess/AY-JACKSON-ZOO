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

    // =========================
    // FIELDS
    // =========================
    private int hatchTime;
    private boolean hatched;
    private Animal parent;
    private int index;

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

    public int getIndex() {
        return index;
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

    public void setIndex(int index) {
        this.index = index;
    }

    // =========================
    // TIME METHODS
    // =========================
    public void passTime(int daysPassed) {
        hatchTime -= daysPassed;
        if (hatchTime < 0) {
            hatchTime = 0;
        }
    }

    public boolean readyToHatch() {
        return hatchTime <= 0;
    }

    // =========================
    // HATCH METHOD
    // =========================
    public Animal hatch(String name) {

        // already hatched
        if (hatched) {
            System.out.println("This egg has already hatched.");
            return null;
        }

        // not ready
        if (!readyToHatch()) {
            System.out.println("The egg is not ready to hatch yet.");
            return null;
        }

        Animal baby = null;

        // ===== CREATE BABY BASED ON PARENT TYPE =====
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

        if (baby == null) {
            System.out.println("Unknown species. Egg failed to hatch.");
            return null;
        }

        // ===== FINALIZE BIRTH =====
        baby.setName(name);

        if (Math.random() < 0.5) {
            baby.setGender("Male");
        } else {
            baby.setGender("Female");
        }
        hatched = true;

        System.out.println(name + " has been born!");
        return baby;
    }

    // =========================
    // HELPER
    // =========================
    private int getInitialHatchTime(Animal parent) {

        if (parent instanceof Shark) {
            return SHARK_HATCH_TIME;
        }
        if (parent instanceof Sunfish) {
            return SUNFISH_HATCH_TIME;
        }
        if (parent instanceof Snake) {
            return SNAKE_HATCH_TIME;
        }
        if (parent instanceof Crocodile) {
            return CROCODILE_HATCH_TIME;
        }
        if (parent instanceof Frog) {
            return FROG_HATCH_TIME;
        }
        if (parent instanceof Axolotl) {
            return AXOLOTL_HATCH_TIME;
        }

        return 0;
    }

    // =========================
    // STRING
    // =========================
    public String toString() {
        return "Egg of " + parent.getSpecie() + "\n" +
               "Hatch Time Remaining: " + hatchTime + " days.";
    }
}
