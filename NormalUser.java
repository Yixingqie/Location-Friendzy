import java.util.*;
import java.io.*;
public class NormalUser extends Account{
   private Calendar calendar;
   private Money balance;
   private String address;
   private Block[] preferredAttraction;
   private int x;
   private int y;
   
   
   // Constructor
   public NormalUser(String username, String phoneNumber, String name, String password,Money balance, String address, int x, int y, Block[] preferred , Calendar calendar){
      super(username,phoneNumber,name,password);
      this.address = address;
      this.balance = balance;
      this.x = x;
      this.y = y;
      preferredAttraction = preferred;
      this.calendar = calendar;
      
   }
   
   // Accessors
   public int getX(){
      return x;
   }
	
   public int getY(){
      return y;
   }
   
   public Block getPreferredAttraction (int i) {
      if (i == 1){
         return preferredAttraction[0];
      } 
      else if(i == 2){
         return preferredAttraction[1];
      }
      else {
         return preferredAttraction[2];
      }
   }
   
   public String getAddress(){
      return address;
   }
     
   public Calendar getPCalendar(){
      return calendar;
   }
    
   public String getPrintableCalendar(){
      return calendar.toString();
   }
      
   public boolean getAvailability(int i, int j){
      return calendar.getAvailability(i, j);   
   }
   
   public double getBalance(){
      return balance.getBalance();
   }
   
   public Money getMoney(){
      return balance;
   }
   // Mutators
   public void setAddress(String address){
      this.address = address;
   }
      
//        public void editCalendar(int day, String times, boolean avail){
//          String [] time = times.split("-");
//          int start = Integer.parseInt(time[0]);
//          int end = Integer.parseInt(time[1]);
//          calendar.editCalendar(this, day, start, end, avail);
//       }

   // User can edit their preferred location
   public void editPreferredLocation(int type, Block attraction){
      preferredAttraction[type-1] = attraction;
     // preferredAttraction = ((Attractions[])attraction);
   }
   
   // Deducts money from the balance of the user      
   public void deductBalance(double amount){
      balance.deductBalance(amount);
   }
      
   // Adds amount to the balance of the user     
   public boolean updateBalance(double amount){
      return balance.updateBalance(amount);
   }
   
   // Returns if this account has the same username as another account
   public boolean equals(Account user){
      if((this.username).equals(user.getUsername())){
         return true;
      }else{
         return false;
      }
   }

   // Unlike toString, this method returns more information about the user
   public void viewAllInfo () {
      System.out.println("Name: " + name);
      System.out.println("Username: " + username);
      System.out.println("Phone Number: " + phoneNumber);
      System.out.println("Budget: " + balance.getWeeklyBudget());
      System.out.println("Address: " + address);
      for (int i = 0; i < 3; i ++) {
         System.out.println(""+(i+1)+". Preferred "+ attractionType(i+1)+" Attraction : " + ((Attractions)preferredAttraction[i]));
      }
   }
   
   private String attractionType(int num){
   switch(num){
      case 1:
      return "Food";
      case 2:
      return "Retail";
      case 3:
      return "Entertainment";
      }
      return null;
   
   }
   
   // toString
   public String toString(){
      return ""+name +"-"+username+"-"+phoneNumber;
   }
      
}