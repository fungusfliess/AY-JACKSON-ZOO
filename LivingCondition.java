

public abstract class LivingCondition {
    private double temperature;
    private double humidity;
    private String region;

    public LivingCondition(double temp, double hum, String region) {
        this.temperature = temp;
        this.humidity = hum;
        this.region = region;
    }  
    public double compareTo(LivingCondition other) {
        double diff = 0;
        diff += Math.min(this.getTemperature(), other.getTemperature())/Math.max(this.getTemperature(), other.getTemperature());
        diff += Math.min(this.getHumidity(), other.getHumidity())/Math.max(this.getHumidity(), other.getHumidity());
        return diff/3;
    }

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
    // METHODS
    public String toString() {
        return "Temperature: " + temperature + "\n" +
               "Humidity: " + humidity + "\n" +
               "Region: " + region + "\n";
    }
}
