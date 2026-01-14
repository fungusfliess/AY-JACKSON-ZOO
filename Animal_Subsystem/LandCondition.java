package Animal_Subsystem;

public class LandCondition extends LivingCondition {
    private double soilType; // Represented as a numeric value for simplicity
    private double landSlope; // in degrees
    private boolean hasWaterSource;
    private double vegetationDensity; // percentage from 0 to 100
    private int amountStructures; // number of structures present

    public LandCondition(double temp, double hum, String region, 
                         double soilType, double slope, boolean hasWater, double vegetation, int structures) {
        super(temp, hum, region);
        this.soilType = soilType;
        this.landSlope = slope;
        this.hasWaterSource = hasWater;
        this.vegetationDensity = vegetation;
        this.amountStructures = structures;
    }

    @Override
    public double compareTo(LivingCondition other) {
        if (!(other instanceof LandCondition)) {
            return 0;
        }
        LandCondition otherLandCondition = (LandCondition) other;
        if (this.hasWaterSource != otherLandCondition.hasWaterSource) {
            return 0; 
        }
        double diff = 0;
        diff += Math.min(this.getTemperature(), other.getTemperature())/Math.max(this.getTemperature(), other.getTemperature());
        diff += Math.min(this.getHumidity(), other.getHumidity())/Math.max(this.getHumidity(), other.getHumidity());
        diff += Math.min(this.soilType, otherLandCondition.soilType)/Math.max(this.soilType, otherLandCondition.soilType);
        diff += Math.min(this.landSlope, otherLandCondition.    landSlope)/Math.max(this.landSlope, otherLandCondition.landSlope);
        diff += Math.min(this.vegetationDensity, otherLandCondition.vegetationDensity)/Math.max(this.vegetationDensity, otherLandCondition.vegetationDensity);
        diff += Math.min(this.amountStructures, otherLandCondition.amountStructures)/Math.max(this.amountStructures, otherLandCondition.amountStructures);
        return diff / 7;
    }
}
