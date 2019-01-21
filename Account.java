import java.util.*;
import java.io.*;
class Account{
   protected String name;
   protected String phoneNumber;
   protected String username;
   protected String password;
   
   // Constructors
   public Account(String username, String phoneNumber,String name, String password){
      this.name = name;
      this.phoneNumber = phoneNumber;
      this.username = username;
      this.password = password;
   }
      
   public Account(){
      
   }
   
   // Accessors
   public String getName(){
      return name;
   }
   
   public String getPhoneNumber(){
      return phoneNumber;
   }
   
   public String getUsername(){
      return username;
   }
   
   public String getPassword(){
      return password;
   }
   
   // Mutators
   public void setName(String name){
      this.name = name;
   }
   
   public void setPhoneNumber(String phoneNumber){
      this.phoneNumber = phoneNumber;
   }
   
   public void setUsername(String username){
      this.username = username;
   }
   
   public void setPassword(String password){
      this.password = password;
   }
   
   // Checks if the given username matches this username
   public boolean validUsername (String userN) {
      if (this.username.equals(userN)) {
         return false;
      }
      return true;
   }
   
   // returns true if username and password equals other username and password
   public boolean equals(String username, String password){
//   System.out.println(this.username.equals(username));
//   System.out.println(this.password.equals(password));
      if ((this.username.trim()).equals(""+username.trim()) && (this.password.trim()).equals(password.trim()))
      {
         return true;
      }
      return false;
   }    
}