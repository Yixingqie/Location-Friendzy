import java.util.*;
import java.io.*;
class Calendar {
 //  private Account normUser;
 private String username;
   protected static final int NUM_DAYS = 7;
   protected static final int NUM_HOURS = 24;
   private boolean [][] calendar = new boolean[NUM_DAYS][NUM_HOURS];
   
   // Constructor
   public Calendar (String username, String lines) {
     // normUser = normal;
      convertCalendarToBoolean(lines);
      this.username = username;
   }
   
   // Accessors
   public String getUsername () {
      return username;
   }
   
   public boolean getAvailability(int i, int k){
      return calendar[i][k];
   }

      public boolean [][] getCalendar () {
      return calendar;
   }
   
   // Recieves a String which contains hours and converts it to an array of boolean called calendar
   public void convertCalendarToBoolean (String line) {
   //       try {
   //          BufferedReader in = new BufferedReader(new FileReader(FILENAME));
   
      String [] input = line.split("\n");
      
      for (int i = 0; i < NUM_DAYS; i ++) {      
         boolean within = false;
         String [] times = input[i].split(" ");
         int count = 1;
         for (int j = 0; j < NUM_HOURS; j ++) {
            if ((Integer.parseInt(times[count]) == j) || (within && j < (Integer.parseInt(times[count + 1])))) {
               within = true;
               calendar[i][j] = true;
            } else if ((Integer.parseInt(times[count + 1]) == j)) {
               calendar[i][j] = true;
               if (times.length > count + 2) {
                  count += 2;
               }
               within = false;
            } else {
               calendar[i][j] = false;
            }
         }
      }
   //          in.close();
   //       } catch (IOException iox) {
   //          System.out.println("NO");
   //       }
   }
   
   // Using this method the user can change different hours of their calendar to free or busy
   public void editCalendar (int day, int startHour, int endHour, boolean free) {
      for (int i = startHour; i < endHour; i ++) {
         calendar[day][i] = free;
      }
   }
   
   // Re-writes the original text file if any variables have been updated
   public String updateTextToFile() {
      String temp = "";
      temp += username + "\n";
   //       try {
   //          BufferedWriter out = new BufferedWriter(new FileWriter(FILENAME));
      for (int i = 0; i < NUM_DAYS; i ++) {
         temp += i;
         boolean timeframe = false;
         for (int j = 0; j < NUM_HOURS; j ++) {
            if (calendar[i][j] == true && !timeframe) {
               temp += " " + j;
               timeframe = true;
            } else if (j + 1 < NUM_HOURS && calendar[i][j] == true && calendar[i][j + 1] == false) {
               temp += " " + j;
               timeframe = false;
            } else if (j + 1 == NUM_HOURS && calendar[i][j] == true) {
               temp += " " + j;
            }
         }
         temp += "\n";
      }
   //          out.close();
   //       } catch (IOException iox) {
   //          System.out.println("NO");
   //       }
      return temp;
   }
   
   // Converts calendar to String 
   public String toString(){
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
            if (calendar[i][j] ) {
               cal += "T  ";
            } else {
               cal += "F  ";
            }
         //cal += calendar[i][j] + "\t";
         }
         cal+="\n";
      }
      return cal;
   }
}