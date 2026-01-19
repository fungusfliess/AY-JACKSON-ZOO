/*
   File Name: Egg.java
   Name: Jerry Ning
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Egg represents an animal egg that can hatch after a species-specific
                incubation period. Used for breeding and reproduction in the zoo.
*/
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
    
    /* @description: Creates an egg from a parent animal with species-specific hatch time
       @param parent the parent animal that laid this egg
    */
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
    
    /* @description: Decrements the hatch time by one day
    */
    public void passDay() {
        hatchTime--;
    }

    /* @description: Checks if the egg is ready to hatch (hatch time reached)
       @return true if hatch time is 0 or less, false otherwise
    */
    public boolean readyToHatch() {
        return hatchTime <= 0;
    }

    // =========================
    // HATCH METHOD
    // =========================
    
    /* @description: Hatches the egg into a baby animal of the parent's species.
                     Checks if already hatched or not ready, creates appropriate species,
                     assigns name and random gender.
       @param name the name for the newly hatched animal
       @return the baby Animal if successful, null if hatching fails
    */
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

        // Create baby of appropriate species based on parent type
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

        // Assign name to baby
        baby.setName(name);
        // Randomly assign gender
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
    
    /* @description: Determines the hatch time based on parent species
       @param parent the parent animal
       @return the species-specific hatch time in days
    */
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
    
    /* @description: Converts the egg data to a string format for file storage
       @return formatted string with parent name, species group, and hatch time
    */
    public String saveToString() {

        String group = "";

        // Determine species group for saving
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
