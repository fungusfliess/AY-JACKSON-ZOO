package Animal_Subsystem;

import Structure_Subsystem.*;

public class Axolotl extends Amphibian {

    private static final LivingCondition AXOLOTL_CONDITION =
        new WaterCondition(16, 70, "Freshwater", 7.4, 150, 15, true, 0);

    public Axolotl(Animal parent) {
        super(parent);
        setupStats();
    }

    public Axolotl(char habitatId, String name, String preferedInteraction, String gender,
                   int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, "Axolotl", preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);

        setupStats();
        updateAge();
    }

    private void setupStats() {
        setMaxHunger(20);
        setTypeFoods(new String[]{"Insects", "Small Fish"});
        setLifeExpectancy(15);
        setFlexibility(0.7);
        setLivingCondition(AXOLOTL_CONDITION);
        setTotalDailyInteractions(5);
        setAdultAge(3);
        setRequiredArea(8.0);

        stage2Age = 1;
        stage3Age = 2;
        canWalk = false;
        canBreathUnderwater = true;
    }

    // ===== ABSTRACT GETTERS =====

    public int getMaxHunger() {
        return 20;
    }

    public String[] getTypeFoods() {
        String[] foods = {"Insects", "Small Fish"};
        return foods;
    }

    public int getLifeExpectancy() {
        return 15;
    }

    public double getFlexibility() {
        return 0.7;
    }

    public LivingCondition getLivingCondition() {
        return AXOLOTL_CONDITION;
    }

    public int getTotalDailyInteractions() {
        return 5;
    }

    public int getAdultAge() {
        return 3;
    }

    public double getRequiredArea() {
        return 8.0;
    }

    // ===== METHODS =====

    public void updateAge() {
        setWeight(getWeight() + 5);
        setMaxHunger(getMaxHunger() + 2);

        if (getAge() > stage3Age) {
            currentStage = STAGE_ADULT;
            canWalk = true;
            canBreathUnderwater = false;
        } else if (getAge() > stage2Age) {
            currentStage = STAGE_JUVENILE;
            canWalk = true;
            canBreathUnderwater = true;
        } else {
            currentStage = STAGE_LARVA;
            canWalk = false;
            canBreathUnderwater = true;
        }
    }

    public String description() {
        return super.description() +
               "Axolotls are amphibians that remain in their larval stage and live fully underwater.";
    }
}
