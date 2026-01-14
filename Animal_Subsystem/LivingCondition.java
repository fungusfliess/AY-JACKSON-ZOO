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
    public double compareTo(LivingCondition other) {
        double diff = 0;
        diff += Math.min(this.getTemperature(), other.getTemperature())/Math.max(this.getTemperature(), other.getTemperature());
        diff += Math.min(this.getHumidity(), other.getHumidity())/Math.max(this.getHumidity(), other.getHumidity());
        diff += Math.min(this.getArea(), other.getArea())/Math.max(this.getArea(), other.getArea());
        return diff/3;
    }

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
    // METHODS
    public String toString() {
        return "Temperature: " + temperature + "\n" +
               "Humidity: " + humidity + "\n" +
               "Area: " + area + "\n" +
               "Region: " + region + "\n";
    }
}