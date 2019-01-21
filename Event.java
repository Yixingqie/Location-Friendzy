import java.util.*;
import java.io.*;
class Event {
  // private Block [] location;
  //  private Block meetingSpot;
   private Block attraction;
   private int numPeople;
   private String date;
   private int eventID;
   private int groupID; 
   private NormalUser [] listOfUsers;;
   private Group group;
   private final String pending = "pending.txt";
 //  int numVotes = 0;
  // int yes = 0;
   
   // Constructors
   public Event(){
   }
   
   public void syncUsers(){
      listOfUsers = group.getGroupMembers();
   }
   
   public Group getGroup () {
      return group;
   }
   
   public Event (int eventID,int numPeople, Group group, String date, Block place) { /////////////file constructor
      this.eventID = eventID;
      this.group = group;
      syncUsers();
      this.numPeople = numPeople;
      this.date = date;
      this.attraction = place;
     
   }

 
   public Event (int eventID,int numPeople, Group group, String date, int attractionType) {
      this.eventID = eventID;
      this.group = group;
      syncUsers();
      this.numPeople = group.getNumberOfMembers();
      this.attraction = findBestLocation(arrayOfAttractions(attractionType));
      this.date = date;

     
   }
   
   public Event (NormalUser [] people) {
      listOfUsers = people;
      numPeople = people.length;
      date = null;
   }

   // Accessors
   public String getDate () {
      return date;
   }
   
   public int getNumPeople(){
      return numPeople;
   }
	
   public int getEventID () {
      return eventID;
   }
   
   public int getGroupID(){
      return group.getID();
   }
   
   public Block getBlock () {
      return attraction;
   }
   public String getBlockName() {
      return ((Attractions)attraction).toString();
   }

   // Mutators
   public void setEventID(int id){ 
      eventID = id;
   }
   
   public void setDate(String time) {
      date = time;
   }
//    public void addLocation (Block place) {
//       String [] temp = (place.toString()).split("\n");
//       try {
//          BufferedWriter out = new BufferedWriter(new fileWriter(pending));
//          for (int i = 0; i < temp.length; i++) {
//             out.write(temp[i]);
//          }
//          out.close();
//       } catch (IOException iox) {
//          System.out.println("NO");
//       }
//       pendingEvent();
//    } 
   
//    public void approveNewLocation (int choice) {
//       numVotes ++;
//       if (choice == 1) {
//          yes ++;
//       } else {
//          yes --;
//       }
//       if (numVotes == peopleInGroup) {
//          if (yes > 0) {
//             location[numLocations] = new Block (Block.getName());
//          }
//          yes = 0;
//          numVotes = 0;
//       }
//    }
   
   // Finds the best location for users to visit based on locations picked by group of users
   public Block findBestLocation(Block [] attractions){
      int howMany = 0;
      for (int i = 1; i < numPeople; i ++) {
         int j = i;
         while (j > 0) {
            if (((Attractions)attractions[j]).getName().compareTo(((Attractions)attractions[j - 1]).getName()) < 0) {
               Block temp = attractions[j];
               attractions[i] = attractions[j - 1];
               attractions[j - 1] = temp;
            }
            j --;
         }
      }  
      int [] count = new int[attractions.length];
      Block [] instances = new Block[attractions.length];
      int number = 0;
      int mostFrequent = 0;
      int biggest = 0;
      for (int i = 0; i < numPeople; i++){
         count[i] = 0;
      }
//       for(int i = 0; i < attractions.length; i++){
//          instances[i] = null;
//       }
//       instances[0] = attractions[0];
      boolean b = false;
      Attractions current = (Attractions)attractions[0];
      instances[0] = attractions[0];
      for (int i = 0; i < numPeople; i++){
         b = false;
         if (current.equals((Attractions)attractions[i])) {
            count[howMany] ++;
         } else if (!(current).equals((Attractions)attractions[i])) {
            howMany ++;
            count[howMany] ++;
            current = (Attractions)attractions[i];
            instances[howMany] = current;
         }
      }  
      for (int i = 0; i < count.length; i++){
         if (count[i] > biggest){
            biggest = count[i];
            mostFrequent = i;
         }
      } 
      return instances[mostFrequent];
   }
   // public Block findBestLocation (Block [] places) {
//       Block invalid = null;
//       for (int i = 1; i < numPeople; i ++) {
//          int j = i;
//          while (j > 0) {
//             if (places[j].getName().compareTo(places[j - 1].getName()) < 0) {
//                Block temp = places[j];
//                places[i] = places[j - 1];
//                places[j - 1] = temp;
//             }
//             j --;
//          }
//       }  
//       boolean found = false;
//       int most = 1;
//       int count = 1;
//       int mostFrequentCount = 1;
//       int current = 1; 
//       for (int i = 0; i < numPeople; i ++) {
//          if (places[i].equals(invalid)) {
//             places[i] = null;
//          } else if (places[i].equals(location[current])) {
//             count ++;
//             mostFrequentCount ++;
//          } else if (!(location[i].equals(location[current]))) {
//             if (mostFrequentCount < count) {
//                mostFrequentCount = count;
//                most = i - 1;
//                count = 1;
//                current = i;
//             }
//          }
//       }
//       return location[most];
//    
//    }
   
   // Returns a list of attractions that are most suitable for the event based on
 //   // attractions picked by the users
//    public Block [] listOfBestAttractions (int d) {
//       Block temp = null;
//       Block [] list = new Block [numPeople];
//       for (int i = 0; i < numPeople; i ++) {
//          list[i] = ((NormalUser)listOfUsers[i]).getPreferredAttraction(d);
//       }
//       Block [] attractions = new Block[numPeople];
//       boolean moreLeft = true;
//       for (int i = 0; i < numPeople && moreLeft; i ++) {
//          temp = findBestLocation(list);
//          attractions[i] = temp;
//          if (temp == null) {
//             moreLeft = false;
//          }
//       }
//       return attractions;
//    }
   
   public Block[] arrayOfAttractions(int d){
      Block[] attractions = new Block[numPeople];
      for(int i = 0; i < numPeople; i++){
         attractions[i] = listOfUsers[i].getPreferredAttraction(d);
        // System.out.println(attractions[i]);     
      }
      return attractions;
   }
   
   // Rewrites the text file of the event in case any variables have been changed
   // public void toTextFile (String eventFile) {
//       try {
//          BufferedWriter out = new BufferedWriter(new FileWriter(eventFile));
//          out.write("" + eventID);
//          out.write("" + numPeople);
//          out.write(date);
//          for (int i = 0; i < numPeople; i ++){
//             String [] people = (listOfUsers[i].toString()).split("\n");
//             for (int j = 0; j < people.length; j ++) {
//                out.write(people[i]);
//             }
//             
//          }
//          out.close();
//       } catch (IOException iox) {
//          System.out.println("NO");
//       }
//    }
   
   // Returns if a user is in the event
   public boolean userInEvent (Account user) {
      if(group.userInGroup(user)){
         return true;
      }
      return false;
         // if (((NormalUser)user).getUsername().equals(((NormalUser)listOfUsers[i]).getUsername())) {
   //             return true;
   }
   
   // Compares the event ID of to event to check if they are the same
   public boolean equals(Event eve){
      return (this.eventID == eve.getEventID());
   }
	
   public void changeAttraction (int choice) {
   
   }
   // toString
   public String toString () {   
      String people = "";
      for (int i = 0; i < numPeople; i++) {
      //System.out.println(listOfUsers[i].getUsername());
         people += listOfUsers[i].getUsername() + "\n";
      }
      return  "ID: "+ eventID + "\nDate: " + date + "\nAttraction: " + attraction + "\nMembers: \n" + people ;
   //  return "no";
   }
}