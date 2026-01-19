/*
   File Name: WaterCondition.java
   Name: Jerry Ning
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: WaterCondition represents aquatic living conditions for water-dwelling animals.
                It includes water-specific properties like acidity, hardness, temperature, and salinity.
*/

public class WaterCondition extends LivingCondition {
    private double waterAcidity;
    private double waterHardness;
    private double waterTemperature;
    private boolean hasLand;
    private double waterSalinity;

    /* @description: Creates aquatic living conditions with water-specific parameters
       @param temp air temperature
       @param hum humidity
       @param region geographic region
       @param acidity water pH level
       @param hardness mineral content of water
       @param temperature water temperature
       @param hasLand whether the habitat includes land areas
       @param salinity salt content of water
    */
    public WaterCondition(double temp, double hum, String region, 
                          double acidity, double hardness, double waterTemperature, boolean hasLand, double salinity) {
        super(temp, hum, region);
        this.waterAcidity = acidity;
        this.waterHardness = hardness;
        this.waterTemperature = waterTemperature;
        this.hasLand = hasLand;
        this.waterSalinity = salinity;
    }

    /* @description: Compares water conditions, returning 0 if not compatible (different types,
                     land requirements, or regions), otherwise calculates similarity based on
                     water properties and base environmental factors
    */
    @Override
    public double compareTo(LivingCondition other) {
        if (!(other instanceof WaterCondition)) {
            return 0; // Not compatible with non-water conditions
        }
        WaterCondition otherWaterCondition = (WaterCondition) other;
        // Must have matching land availability and region
        if (this.hasLand != otherWaterCondition.hasLand || !(this.getRegion().equalsIgnoreCase(other.getRegion()))) {
            return 0; 
        }
        double diff = 0;
        // Compare water-specific properties
        diff += Math.min(this.waterAcidity, otherWaterCondition.waterAcidity)/Math.max(this.waterAcidity, otherWaterCondition.waterAcidity);
        diff += Math.min(this.waterHardness, otherWaterCondition.waterHardness)/Math.max(this.waterHardness, otherWaterCondition.waterHardness);
        diff += Math.min(this.waterTemperature, otherWaterCondition.waterTemperature)/Math.max(this.waterTemperature, otherWaterCondition.waterTemperature);
        diff += Math.min(this.waterSalinity, otherWaterCondition.waterSalinity)/Math.max(this.waterSalinity, otherWaterCondition.waterSalinity);
        diff /= 4;
        // Combine with base environmental comparison
        return (diff+ super.compareTo(other))/2;
    }

    // METHODS
    public String toString() {
        return super.toString() +
               "Water Acidity: " + waterAcidity + "\n" +
               "Water Hardness: " + waterHardness + "\n" +
               "Water Temperature: " + waterTemperature + "\n" +
               "Has Land: " + hasLand + "\n" +
               "Water Salinity: " + waterSalinity + "\n";
    }

    // returns formatted String to save into a file.
    public String saveToString() {
        return "WATER" + "\n" 
                    + super.saveString() 
                    + waterAcidity + "\n" 
                    + waterHardness + "\n"
                    + waterTemperature + "\n"
                    + hasLand + "\n"
                    + waterSalinity + "\n";
    }
}
