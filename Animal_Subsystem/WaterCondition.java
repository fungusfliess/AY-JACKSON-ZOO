package Animal_Subsystem;

public class WaterCondition extends LivingCondition {
    private double waterAcidity;
    private double waterHardness;
    private double waterTemperature;
    private boolean hasLand;
    private double waterSalinity;

    public WaterCondition(double temp, double hum, double area, String region, 
                          double acidity, double hardness, double temperature, boolean hasLand, double salinity) {
        super(temp, hum, area, region);
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
        if (this.hasLand != otherWaterCondition.hasLand) {
            return 0; 
        }
        double diff = 0;
        diff += Math.min(this.getTemperature(), other.getTemperature())/Math.max(this.getTemperature(), other.getTemperature());
        diff += Math.min(this.getHumidity(), other.getHumidity())/Math.max(this.getHumidity(), other.getHumidity());
        diff += Math.min(this.getArea(), other.getArea())/Math.max(this.getArea(), other.getArea());
        diff += Math.min(this.waterAcidity, otherWaterCondition.waterAcidity)/Math.max(this.waterAcidity, otherWaterCondition.waterAcidity);
        diff += Math.min(this.waterHardness, otherWaterCondition.waterHardness)/Math.max(this.waterHardness, otherWaterCondition.waterHardness);
        diff += Math.min(this.waterTemperature, otherWaterCondition.waterTemperature)/Math.max(this.waterTemperature, otherWaterCondition.waterTemperature);
        diff += Math.min(this.waterSalinity, otherWaterCondition.waterSalinity)/Math.max(this.waterSalinity, otherWaterCondition.waterSalinity);
        return diff / 7;
    }
}
