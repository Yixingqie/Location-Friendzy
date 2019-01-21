import java.util.*;
import java.io.*;
class Money {
   private double balance;
   private String creditNumber;
   private String expirationDate;
   private int cvvNumber;
   private int weeklyBudget;
   
   // Constructor
   public Money(double bal, String credNum, String expDate, int cvvNum, int weekBud) {
      balance = bal;
      creditNumber = credNum;
      expirationDate = expDate; 
      cvvNumber = cvvNum;
      weeklyBudget = weekBud; 
   }
   
   // Accessor
    public double getBalance() {
      return balance;
   }
   
    public String getCredNum(){
      return creditNumber;
    }
    public String getExpDate(){
   return expirationDate;
   }
   public int getCVV(){
   return cvvNumber;
   }
   public int getWeeklyBudget(){
   return weeklyBudget;
   }
   
   // Another user can borrow money from this account
   public boolean borrowMoney(Money mon, double amount) {
      boolean borrow = false;
      if (mon.balance - amount > 0) {
         mon.balance -= amount;
         this.balance += amount;
         borrow = true; 
      }
      return borrow;
   }
   
   // This account can lend money to another user
   public boolean loanMoney(Money mon, double amount) {
      boolean loan = false; 
   
      if (this.balance - amount >= 0) {
         this.balance -= amount;
         mon.balance += amount;
         loan = true; 
      }
      return loan; 
   }
   
   // Adds new amount to the balance
   public boolean updateBalance(double amount) {
      boolean added = false;
      if (amount > 0) {
         added = true;
         balance += amount;
         return added;
      }
      return added;  
   }
   
   // Subtracts amount from the balance   
   public void deductBalance(double amount){
      balance -= amount;
   }
}