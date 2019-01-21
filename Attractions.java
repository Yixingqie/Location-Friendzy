import java.util.*;
import java.io.*;
public abstract class Attractions extends Block {
   private final int DAYS = 7;
   private final int HOURS = 24;
   private final double TAX = 1.13;
   
   private int id; 
   private String nameL;
   private String phoneNumber;
   private double maxPrice; 
   private double minPrice;
   private int startHour;
   private int endHour;
   
   private int close;
   private int open;

   // Constructor   
   public Attractions(int i, String phoneNum, String name, String addr, int corx, int cory, double maxP, double minP, int open, int close) {
      super(addr,corx,cory);
      id = i;
      nameL = name; 
      phoneNumber = phoneNum;
      maxPrice = maxP;
      minPrice = minP;
      this.close = close;
      this.open = open;	  
   }
   
   // Accessors
   public int getID(){
      return id;
   }

   public String getName() {
      return nameL;
   }
   
   public String getPhoneNumber(){
      return phoneNumber;
   }
   
   public double getMaxPrice(){
      return maxPrice;
   }
   
   public double getMinPrice(){
      return minPrice;
   }
   
   public int getStartHour(){
      return open;
   }
   
   public int getEndHour(){
      return close;
   }
   
 
   
//    // Mutators
//    public void setID(int id){
//       this.id = id;
//    }
// 
//    public void setName(String name){
//       this.name = name;
//    }
//    
//    public void setPhoneNumber(String phoneNumber){
//       this.phoneNumber = phoneNumber;
//    }
// 
//    public 

   // ...
   public boolean checkPrice(double amount) {
      boolean checked = false;
      if(maxPrice == amount) {
         checked = true;
         return checked;
      }
      return checked;
   }
   
   
   // Generates popular hours in which the attraction is the busiest          
   private void generatePopularHours() {
      double h = Math.random() * 10;
      String hrs = ((int)h) + "";
   }
   
   // Administrator methods to change variables   
   public void editAttractionAddress(String address){
      this.address = address;
   }
   public void editAttractionName(String name){
      nameL = name;
   }
   
   public void editAttractionPhoneNumber(String phone){
      phoneNumber = phone;
   }
 
   public void editAttractionMinPrice(double newMinPrice){
      minPrice = newMinPrice;
   }
   
   public void editAttractionMaxPrice(double newMaxPrice){
      maxPrice = newMaxPrice;
   }
   
   // Abstract mehtods
   public abstract void deductMoney(double amount, NormalUser user);
   
   public abstract Block getAttraction();
   
   // toString
   public String toString(){
      return nameL;
   }
   
   public boolean equals (String name) {
      if ((this.nameL).equals(name)) {
         return true;
      }
      return false;
   }
}