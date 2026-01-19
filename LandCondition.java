/*
   File Name: LandCondition.java
   Name: Jerry Ning
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: LandCondition represents terrestrial living conditions for land-dwelling animals.
                It includes land-specific properties like soil compaction, slope, vegetation, and structures.
*/

public class LandCondition extends LivingCondition {
    private double soilCompaction; // 0-100 (higher = more compact)
    private double landSlope; // in degrees
    private boolean hasWaterSource;
    private double vegetationDensity; // percentage from 0 to 100
    private int amountStructures; // number of structures present

    /* @description: Creates terrestrial living conditions with land-specific parameters
       @param temp air temperature
       @param hum humidity
       @param region geographic region
       @param soilCompaction how compact the soil is (0-100)
       @param slope terrain slope in degrees
       @param hasWater whether water sources are available
       @param vegetation vegetation coverage percentage
       @param structures number of nearby structures
    */
    public LandCondition(double temp, double hum, String region, 
                         double soilCompaction, double slope, boolean hasWater, double vegetation, int structures) {
        super(temp, hum, region);
        this.soilCompaction = soilCompaction;
        this.landSlope = slope;
        this.hasWaterSource = hasWater;
        this.vegetationDensity = vegetation;
        this.amountStructures = structures;
    }

    /* @description: Compares land conditions, returning 0 if not compatible (different types,
                     water requirements, or regions), otherwise calculates similarity based on
                     terrain properties and base environmental factors
    */
    @Override
    public double compareTo(LivingCondition other) {
        if (!(other instanceof LandCondition)) {
            return 0; // Not compatible with non-land conditions
        }
        LandCondition otherLandCondition = (LandCondition) other;
        // Must have matching water availability and region
        if (this.hasWaterSource != otherLandCondition.hasWaterSource || !(this.getRegion().equalsIgnoreCase(other.getRegion()))) {
            return 0; 
        }
        double diff = 0;
        // Compare land-specific properties
        diff += Math.min(this.landSlope, otherLandCondition.landSlope)/Math.max(this.landSlope, otherLandCondition.landSlope);
        diff += Math.min(this.vegetationDensity, otherLandCondition.vegetationDensity)/Math.max(this.vegetationDensity, otherLandCondition.vegetationDensity);
        diff += Math.min(this.amountStructures, otherLandCondition.amountStructures)/Math.max(this.amountStructures, otherLandCondition.amountStructures);
        diff += Math.min(this.soilCompaction, otherLandCondition.soilCompaction)/Math.max(this.soilCompaction, otherLandCondition.soilCompaction);
        diff /= 4;
        // Combine with base environmental comparison
        return (diff+ super.compareTo(other))/2;
    }

    // METHODS
    public String toString() {
        return super.toString() +
               "Soil Compaction: " + soilCompaction + "\n" +
               "Land Slope: " + landSlope + "\n" +
                "Has Water Source: " + hasWaterSource + "\n" +
                "Vegetation Density: " + vegetationDensity + "\n" +
                "Amount of Structures: " + amountStructures + "\n";
    }

    // returns formatted String to save into a file.
    public String saveToString() {
        return "LAND" + "\n" 
                    + super.saveString() 
                    + soilCompaction + "\n" 
                    + landSlope + "\n"
                    + vegetationDensity + "\n"
                    + hasWaterSource + "\n"
                    + amountStructures + "\n";
    }
}
