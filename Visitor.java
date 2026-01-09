/*
   File Name: Visitor.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: 
*/

public abstract class Visitor extends Person{
    public static final int MIN_ADULT_AGE = 18;
    public static final int MIN_SENIOR_AGE = 65;

    public static final double ADULT_BASE_PRICE = 10.00;
    public static final double CHILD_BASE_PRICE = 5.00;

    public static final int FREE_CHILD_AGE_MAX = 3;
    public static final int FREE_SENIOR_AGE_MIN = 80;
    
    private double balance;
    private int learningLevel;
    private int visitDuration;
    private int attractionsVisited;

    private int learningHistorySize;
    private String[] learningHistory;

    private int numItems;
    private Item[] itemInventory;

    private double amountSpent;

    public Visitor(int age, String personID, String firstName, String lastName, double balance, int learningLevel, int visitDuration)

    public double getBalance()
    public int getLearningLevel()
    public int getAttractionsVisited()
    public int getNumItems()


    public Visitor(int age, String personID, String firstName, String lastNamedouble balance, int learningLevel, int visitDuration) {
        super(age, personID, firstName, lastName);

        this.balance = Math.max(0.0, balance);
        this.learningLevel = Math.max(0, learningLevel);
        this.visitDuration = Math.max(0, visitDuration);

        this.attractionsVisited = 0;
        this.amountSpent = 0.0;

        this.learningHistorySize = 0;
        this.learningHistory = new String[10];

        this.numItems = 0;
        this.itemInventory = new Item[10];
    }


}
