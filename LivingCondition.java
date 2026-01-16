
import java.util.*;
public abstract class LivingCondition {

    public static Scanner sc = new Scanner(System.in);
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

    public static LivingCondition createLivingCondition(){
        String climateType;
        double temp;
        double humidity;
        String region;

        LivingCondition climate;

        System.out.print("Enter climate type (LAND or WATER): ");
        climateType = sc.nextLine();

        System.out.print("Enter temperature: ");
        temp = Double.parseDouble(sc.nextLine());

        System.out.print("Enter humidity: ");
        humidity = Double.parseDouble(sc.nextLine());

        System.out.print("Enter region: ");
        region = sc.nextLine();

        if(climateType.equals("LAND")){
            System.out.print("Enter soil compaction: ");
            double soilCompaction = Double.parseDouble(sc.nextLine());

            System.out.print("Enter land slope: ");
            double landSlope = Double.parseDouble(sc.nextLine());

            System.out.print("Has water source (true/false): ");
            boolean hasWaterSource = Boolean.parseBoolean(sc.nextLine());

            System.out.print("Enter vegetation density: ");
            double vegetationDensity = Double.parseDouble(sc.nextLine());

            System.out.print("Enter number of nearby structures: ");
            int amountStructures = Integer.parseInt(sc.nextLine());

            climate = new LandCondition(
                temp,
                humidity,
                region,
                soilCompaction,
                landSlope,
                hasWaterSource,
                vegetationDensity,
                amountStructures
            );
        }else{
            System.out.print("Enter water acidity: ");
            double waterAcidity = Double.parseDouble(sc.nextLine());

            System.out.print("Enter water hardness: ");
            double waterHardness = Double.parseDouble(sc.nextLine());

            System.out.print("Enter water temperature: ");
            double waterTemp = Double.parseDouble(sc.nextLine());

            System.out.print("Has land access (true/false): ");
            boolean hasLand = Boolean.parseBoolean(sc.nextLine());

            System.out.print("Enter water salinity: ");
            double waterSalinity = Double.parseDouble(sc.nextLine());

            climate = new WaterCondition(
                waterTemp,
                humidity,
                region,
                waterAcidity,
                waterHardness,
                temp,
                hasLand,
                waterSalinity
            );
        }

        return climate;
    }

    public String toString() {
        return "Temperature: " + temperature + "\n" +
               "Humidity: " + humidity + "\n" +
               "Region: " + region + "\n";
    }
    
}