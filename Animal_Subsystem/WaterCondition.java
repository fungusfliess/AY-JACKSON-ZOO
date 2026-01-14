package Animal_Subsystem;

public class WaterCondition extends LivingCondition {
    private double waterAcidity;
    private double waterHardness;
    private double waterTemperature;
    private boolean hasLand;
    private double waterSalinity;

    public WaterCondition(double temp, double hum, String region, 
                          double acidity, double hardness, double temperature, boolean hasLand, double salinity) {
        super(temp, hum, region);
        this.waterAcidity = acidity;
        this.waterHardness = hardness;
        this.waterTemperature = temperature;
        this.hasLand = hasLand;
        this.waterSalinity = salinity;
    }

    @Override
    public double compareTo(LivingCondition other) {
        if (!(other instanceof WaterCondition)) {
            return 0;
        }
        WaterCondition otherWaterCondition = (WaterCondition) other;
        if (this.hasLand != otherWaterCondition.hasLand || !(this.getRegion().equalsIgnoreCase(other.getRegion()))) {
            return 0; 
        }
        double diff = 0;
        diff += Math.min(this.waterAcidity, otherWaterCondition.waterAcidity)/Math.max(this.waterAcidity, otherWaterCondition.waterAcidity);
        diff += Math.min(this.waterHardness, otherWaterCondition.waterHardness)/Math.max(this.waterHardness, otherWaterCondition.waterHardness);
        diff += Math.min(this.waterTemperature, otherWaterCondition.waterTemperature)/Math.max(this.waterTemperature, otherWaterCondition.waterTemperature);
        diff += Math.min(this.waterSalinity, otherWaterCondition.waterSalinity)/Math.max(this.waterSalinity, otherWaterCondition.waterSalinity);
        diff /= 7;
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
}
