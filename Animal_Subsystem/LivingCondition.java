package Animal_Subsystem;
public abstract class LivingCondition {
    private double temperature;
    private double humidity;
    private String region;

    public LivingCondition(double temp, double hum, String region) {
        this.temperature = temp;
        this.humidity = hum;
        this.region = region;
    }  
    public abstract double compareTo(LivingCondition other);

    // GETTERS
    public double getTemperature() {
        return temperature;
    }
    public double getHumidity() {
        return humidity;
    }
    public String getRegion() {
        return region;
    }
    // SETTERS
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
    public void setRegion(String region) {
        this.region = region;
    }
}