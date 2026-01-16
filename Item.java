/*
   File Name: Item.java
   Name: Arianna Liu
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Item represents an item in the zoo store. 
*/
public class Item{

   //FEILDS
   private String name;
   private double price;

   //CONSTRUCTOR
   /* 
       @description: declares and initializes Item object
       @param name    the name of the item
       @param price   the price of the item
   */
   public Item(String name, double price){
      this.name = name;
      this.price = price;    
   }
   
   //ACCESSORS
   
   public double getPrice(){
      return price;
   }

   public String getName(){
      return name;
   }
   
   //TO STRING METHOD
   
   public String toString(){
      return name + ": $ " + price + "\n";
   }  
} 
