package Animal_Subsystem;

public class LandCondition extends LivingCondition {
    private double soilCompaction; // 0-100 (higher =)
    private double landSlope; // in degrees
    private boolean hasWaterSource;
    private double vegetationDensity; // percentage from 0 to 100
    private int amountStructures; // number of structures present

    public LandCondition(double temp, double hum, double area, String region, 
                         double soilCompaction, double slope, boolean hasWater, double vegetation, int structures) {
        super(temp, hum, area, region);
        this.soilCompaction = soilCompaction;
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
        if (this.hasWaterSource != otherLandCondition.hasWaterSource || !(this.getRegion().equalsIgnoreCase(other.getRegion()))) {
            return 0; 
        }
        double diff = 0;
        diff += Math.min(this.landSlope, otherLandCondition.    landSlope)/Math.max(this.landSlope, otherLandCondition.landSlope);
        diff += Math.min(this.vegetationDensity, otherLandCondition.vegetationDensity)/Math.max(this.vegetationDensity, otherLandCondition.vegetationDensity);
        diff += Math.min(this.amountStructures, otherLandCondition.amountStructures)/Math.max(this.amountStructures, otherLandCondition.amountStructures);
        diff += Math.min(this.soilCompaction, otherLandCondition.soilCompaction)/Math.max(this.soilCompaction, otherLandCondition.soilCompaction);
        diff /= 7;
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
}
