/*
   File Name: LivingCondition.java
   Name: Jerry Ning
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: LivingCondition is an abstract class representing environmental conditions
                for animals in the zoo, including temperature, humidity, and region.
*/

public abstract class LivingCondition {
    private double temperature;
    private double humidity;
    private String region;

    /* @description: Creates a living condition with environmental parameters
       @param temp the temperature in degrees Celsius
       @param hum the humidity percentage (0-100)
       @param region the geographic region type
    */
    public LivingCondition(double temp, double hum, String region) {
        this.temperature = temp;
        this.humidity = hum;
        this.region = region;
    }  
    
    /* @description: Compares this living condition to another, calculating similarity
                     based on temperature and humidity ratios
       @param other the living condition to compare to
       @return a similarity score between 0 and 1 (1 = identical conditions)
    */
    public double compareTo(LivingCondition other) {
        double diff = 0;
        // Calculate temperature similarity ratio
        diff += Math.min(this.getTemperature(), other.getTemperature())/Math.max(this.getTemperature(), other.getTemperature());
        // Calculate humidity similarity ratio
        diff += Math.min(this.getHumidity(), other.getHumidity())/Math.max(this.getHumidity(), other.getHumidity());
        // Return average similarity
        return diff/2;
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

    // to format and give Superclass's info as a String
    public String saveString () {
        return temperature + "\n" +
               humidity + "\n" +
               region + "\n";
    }



    // to save LivingCondition to a file
    public abstract String saveToString();
}
