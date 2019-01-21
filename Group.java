import java.util.*;
import java.io.*;
class Group {
   private int totalGroups;
   private int id;
   private final int NUM_DAYS = 7;
   private final int NUM_HOURS = 24;
   private boolean [][] calendar = new boolean [NUM_DAYS][NUM_HOURS];
 
   private final int MAXPEOPLE = 20;
   private NormalUser [] groupMembers = new NormalUser[MAXPEOPLE];
   private String [] bestTimeSlots;
   private int peopleInGroup; //
	
   // Constructors
   public Group(){
      totalGroups = 0;
      id = 0;
      groupMembers = null;
      bestTimeSlots = null;
      peopleInGroup = 0; //
   
   }
    // Converts calendar to String 
   public String getGroupCal(){
      String cal = "   ";
      for (int i = 0; i < NUM_HOURS; i ++) {
         if (i >= 10) {
            cal += i + " ";
         } else {
            cal += i + "  ";
         }
      }
      cal += "\n";
      for(int i = 0; i < NUM_DAYS; i++){
         cal += (i + 1) + "  ";
         for(int j = 0; j < NUM_HOURS; j++){
            if (calendar[i][j]) {
               cal += "T  ";
            } else {
               cal += "F  ";
            }
         }
         cal+="\n";
      }
      return cal;
   }

   
   public Group (int id, int howMany, Account [] norm) {// group constructor
      this.id = id;
      peopleInGroup = norm.length;
      boolean found = false;
      for(int i =0; i < MAXPEOPLE; i++){
         groupMembers[i] = null;
      }
      for(int i = 0; i< peopleInGroup; i++){
         groupMembers[i]= ((NormalUser)norm[i]);
      }
      createCalendar();
   
   }
   
   // Accessors
   
   public NormalUser [] getGroupMembers () {
      return groupMembers;
   }
   
   public int getNumberOfMembers () {
      return peopleInGroup;
   }

   public int getID(){
      return id;
   }

   // Mutators
   public void setid (int newid) {
      id = newid;
   }
	
   // Creates a new calendar for the group based on the availability of 
   // each user in the group
   private void createCalendar () {
      boolean stillAvail = true;
      for (int j = 0; j < NUM_DAYS; j ++) {
         stillAvail = true;
         for (int k = 0; k < NUM_HOURS; k ++) {
            stillAvail = true;
            for (int l = 0; l < peopleInGroup; l ++) {
               if (stillAvail) {
                  if (!(groupMembers[l].getAvailability(j, k))) {
                     stillAvail = false;
                  }
               }
            }
            if (stillAvail) {
               calendar[j][k] = true;
            }
         }
      }
   }
   
   
   // Writes the calendar to the text file
   public void calendarToTextFile () {
      int startTime = 0;
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter("GroupCalendar.txt"));
         for (int i = 0; i < NUM_DAYS; i ++) {
            out.write("" + i);
            for (int j = 0; j < NUM_HOURS; j ++) {
               if (calendar[i][j]) {
                  if (j == 0) {
                     out.write(j);
                  }
                  if (j == NUM_HOURS - 1) {
                     out.write(j);
                  }
               } else {
                  if (calendar[i][j] == false) {
                     if (j > 0 && (calendar[i][j - 1])) {
                        out.write(" " + (j - 1));
                     }
                  }
               }
            }
            out.newLine();
         }
      } catch (IOException iox) {
         System.out.println("NO");
      }
   }
   
   // Finds the best time slots for users to meet up based on the length of
   // their availability
  //  public String [] findBestTimeSlots () {
//       final int MAX = 3;
//       int currBestTime = 0;
//       int count = 0;
//       int startTime = 0;
//       String timeSlot = "";
//       String [][] bestTimes = new String [NUM_DAYS][MAX];
//       for (int i = 0; i < NUM_DAYS; i ++) {
//          for (int j = 0; j < MAX; j ++) {
//             bestTimes[i][j] = null;
//          }
//       }
//       for (int i = 0; i < NUM_DAYS; i ++) {
//          int numTimes = 0;
//          count = 0;
//          for (int j = 0; j < NUM_HOURS; j ++) {
//             if (calendar[i][j] == true) {
//                if (count > 0 && calendar[i][j - 1] == false) {
//                   startTime = j;
//                }
//                count ++;
//                if (j == NUM_HOURS - 1 && currBestTime < count) {
//                   currBestTime = count;
//                   timeSlot = startTime + " " + (j - 1);
//                   for (int k = 0; k < MAX; k ++) {
//                      String [] hour = bestTimes[i][k].split(" ");
//                      if (bestTimes[i][k] != null && count > Integer.parseInt(hour[1]) - Integer.parseInt(hour[0])) {
//                         for (int l = MAX - 1; l > k + 1; l --) {
//                            bestTimes[i][l] = bestTimes[i][l - 1];
//                         }
//                         bestTimes[i][k] = timeSlot;
//                      }
//                   }
//                   count = 0;
//                }
//             } else {
//                if (count > currBestTime) {
//                   currBestTime = count;
//                   timeSlot = startTime + " " + (j - 1);
//                   for (int k = 0; k < MAX; k ++) {
//                      String [] hour = bestTimes[i][k].split(" ");
//                      if (bestTimes[i][k] != null && count > Integer.parseInt(hour[1]) - Integer.parseInt(hour[0])) {
//                         for (int l = MAX - 1; l > k + 1; l --) {
//                            bestTimes[i][l] = bestTimes[i][l - 1];
//                         }
//                         bestTimes[i][k] = timeSlot;
//                      }
//                   }                
//                   count = 0;
//                }
//             }
//          }
//       }
//       for (int i = 0; i < NUM_DAYS; i ++) {
//          bestTimeSlots[i] = bestTimes[i][0];
//       }
//       return bestTimeSlots;
//    }
   
   public String [] findBestTimeSlots () {
      int [] count  = new int [NUM_DAYS];
      String [] best = new String [NUM_DAYS];
      int counter = 0;
      int currBest = 0;
      String bestTime = "";
      for (int i = 0; i < NUM_DAYS; i ++) {
         counter = 0;
         currBest = 0;
         bestTime = "";
         boolean start = false;
         int startTime=  0;
         int endTime = 0;
         for (int j = 0; j < NUM_HOURS; j ++) {
            if (calendar[i][j] && start == false) {
               startTime = j;
               start =true;
               counter++;
            }else if (calendar[i][j] && start && j!= 23){
               counter++;
            }else if((!calendar[i][j] && start) || (j == 23 && start)){
            if(j==23 && calendar[i][j]){
               counter++;
            }
               if(counter > currBest){
                  currBest = counter-1;
                  endTime = startTime + counter-1;
                  bestTime = ""+startTime+"-"+endTime;
               }
               start = false;
               counter = 0;  
            }
         }
         best[i] = findDay(i)+" at Time: "+bestTime;
         count[i] = currBest;
      }
      
      
      for(int i = 0; i < 7; i++){
        int j = i;
         int item = count[i]; 
         String item2 = best[i];
         while(j > 0 && item > count[j-1]){
            count [j] = count[j-1];
            best[j] = best[j-1];
            j = j-1;
         }                                                           
         count[j] = item;
         best[j] = item2;
      }
      
      
      return best;
   }
   
   private String findDay(int day){
      switch(day){
         case 0:
         return"Monday";
         case 1: 
         return "Tuesday";
         case 2: 
         return "Wednesday";
         case 3: 
         return "Thursday";
         case 4:
         return "Friday";
         case 5:
         return "Saturday";
         case 6:
         return "Sunday";
         
      }
      return null;
   }
   
   // Method to remove a user from a group
   public void removeUser (Account user) {
      boolean shift = false;
      int start = peopleInGroup-1;
      for (int i = 0; i < peopleInGroup; i ++) {
         if (groupMembers[i].getUsername().equals(user.getUsername())) {
            groupMembers[i] = null;
            shift = true;
            start = i;
         }
      }
      if(shift){
         for(int i = start; i < peopleInGroup-1; i++){
            groupMembers[i] = groupMembers[i+1];
         }
         groupMembers[peopleInGroup-1] = null;
         peopleInGroup--;
         System.out.println("Successful Deletion");
      }else{
         System.out.println("Unsuccessful Deletion");
      }      
   			// if (i != peopleInGroup - 1) {
   // 					for (int j = i; j < peopleInGroup - 1; i ++) {
   // 						groupMembers[j] = groupMembers[j + 1];
   // 					}
   // 					groupMembers[peopleInGroup - 1] = null;
   // 					peopleInGroup --;
   // 				}
   // 			} else if (i == peopleInGroup - 1) {
   // 				groupMembers[peopleInGroup - 1] = null;
   // 				peopleInGroup --;
   // 			}
   	
   }
	
   // Add new user to group
   public void addUser (Account user) {
      if (peopleInGroup < MAXPEOPLE) {
         groupMembers[peopleInGroup] = ((NormalUser)user);
         System.out.println("Successful Addition");
         peopleInGroup++;
      }
   }
	
   // Returns if a user is in this group
   public boolean userInGroup (Account user) {
      for (int i = 0; i < peopleInGroup; i ++) {
         if (((NormalUser)user).getUsername().equals(groupMembers[i].getUsername())) {
            return true;
         }
      }
      return false;
   }
   
   // Writes the group to the text file
   public void toTextFile (String groupFile) {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(groupFile));
         out.write("" + id);
         for (int i = 0; i < peopleInGroup; i ++) {
            out.write(""+groupMembers[i]);
         }
         for (int i = 0; i < NUM_DAYS; i ++) {
            out.write(""+bestTimeSlots[i]);
         }
         out.close();
      } catch (IOException iox) {
         System.out.println("NO");
      }
   }
   
   // toString
   public String toString () {
      String temp = "";
      for (int i = 0; i < peopleInGroup; i++) {
         temp += groupMembers[i]+"\n";
      }
      return "ID:"+ id + "\nMembers: \n"+ temp;
   }
   
  
}