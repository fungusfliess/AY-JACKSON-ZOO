package Animal_Subsystem;
public abstract class LivingCondition {
    private double temperature;
    private double humidity;
    private double area;
    private String region;

    public LivingCondition(double temp, double hum, double area, String region) {
        this.temperature = temp;
        this.humidity = hum;
        this.area = area;
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
    public double getArea() {
        return area;
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
    public void setArea(double area) {
        this.area = area;
    }
    public void setRegion(String region) {
        this.region = region;
    }
}