import java.util.*;
import java.io.*;
class LocationFriendzySolar{
   final static String calendarFile = "CALENDAR.txt";
   final static String accountFile = "USERS.txt";
   final static String blockFile = "Blocks.txt";
   final static String mapFile = "MAP.txt";
   final static String groupFile = "Groups.txt";
   final static String eventFile = "Events.txt";
   private final int MAX_ATTRACTION = 12;
   private final int MAX_ACCOUNTS = 10;
   private final int MAX_GROUPS = 10;
   private final int MAXGROUP = 10;
   private final int MAXEVENTS = 10;
   private final int MAXCALENDARS = 10;
   private final int MAXBLOCKS = 100;
   private static int numAttraction = 0;
   private static int numAccount = 0;
   private int numGroup = 0;
   private int numBlock = 0;
   private int numCal = 0;
   private int mapx;
   private int mapy;
   private int numMoney;  
   private int numEvent = 0; 
   private String username;
   private String tempUsername;
   private boolean loggedOut = true;
   private boolean admin = false;
   private Account mainAccount;
   private Map charMap;
   private Block [] blocks = new Block [MAXBLOCKS];
   private Attractions [] listOfAttractions = new Attractions [MAX_ATTRACTION];
   private char [][] map; 
   private Block [][] mapBlock; 
   private Account [] accounts;
   private Event [] events = new Event [MAXEVENTS];
   private Group [] groups = new Group [MAX_ACCOUNTS];
   private Calendar [] calendars = new Calendar [MAXCALENDARS];
   private Scanner sc = new Scanner(System.in);
   
   boolean successful = true;
  // private Money[] money;
	// Main menu that gives user options based on which action to take
   private void mainMenu(){
      boolean repeat  = true;
      while(repeat){
        try{
            while(!loggedOut){
               int choice = -1;
               if(admin){
                  int id;
                  System.out.println("Do you want to edit 1-Blocks or 2-Logout");
                  choice = sc.nextInt();
                  if(choice == 1){
                     System.out.println("Here are all the blocks");
                     viewAttractions();
                     System.out.println("What is the id of the attraction you want to edit?");
                     id = sc.nextInt();
                     System.out.println("Which part of the attraction do you want to edit?");
                     System.out.println("1. Name");
                     System.out.println("2. address");
                     System.out.println("3. phone number");
                     System.out.println("4. max price");
                     System.out.println("5. min price");
                     choice = sc.nextInt();
                     sc.nextLine();
                  // Edit name
                     if (choice == 1){
                        editName(id);
                     }
                     // Edit Address
                     else if (choice == 2){
                        editAddress(id);
                     }
                     // Edit phone number
                     else if (choice == 3){
                        editPhoneNumber(id);
                     }
                     // Edit max price
                     else if (choice == 4){
                        editMaxPrice(id);
                     }
                     // Edit min price
                     else if (choice == 5){
                        editMinPrice(id);
                     }else{
                        System.out.println("Invalid input, try again!");
                     }
                  }else if(choice ==2){
                     saveEverythingAdmin();
                     loggedOut = true;
                     System.out.println("You are logged out.");
                     homeScreen();
                  }else{
                     System.out.println("Invalid input, try again!");
                  }
               
               }
               else{
                  System.out.println("1. View my groups");
                  System.out.println("2. Add or remove users from a group");
                  System.out.println("3. View my calendar");
                  System.out.println("4. Edit my calendar");
                  System.out.println("5. View my events");
                  System.out.println("6. Edit my events");
                  System.out.println("7. View the map");
                  System.out.println("8. Create group");
                  System.out.println("9. Create event"); 
                  System.out.println("10. View Group Calendar");
                  System.out.println("11. View balance");
                  System.out.println("12. Edit balance");
                  System.out.println("13. Delete event");
                  System.out.println("14. Edit Preferred Attraction");////
                  System.out.println("15. Edit personal Info");////
                  System.out.println("16. Delete Account");////
                  System.out.println("17. View Route");
                  System.out.println("18: View Personal Info");
                  System.out.println("19. Logout");
                  System.out.println("What do you want to do?");
                  choice = sc.nextInt();
               // view groups
                  if (choice == 1){ 
                     viewGroup();
                  }
                  // add or remove users from a group
                  else if (choice == 2){
                     int choice2;
                     System.out.println("Here are all the groups you are in");
                     viewGroup();
                     System.out.println("Do you want to: ");
                     System.out.println("1. Remove users from a group");
                     System.out.println("2. Add users to a group");
                     choice2 = sc.nextInt();
                     if (choice2 == 1){
                        int id;
                        String username;
                        Account user;
                        System.out.println("What is the ID of the group?");
                        id = sc.nextInt();
                        sc.nextLine();
                        System.out.println("What is the username of the user to be removed?");
                        tempUsername = sc.nextLine();
                        user = findAccount(tempUsername);
                        findGroup(id).removeUser(user); // Group needs removeUser
                     }
                     else if (choice2 == 2){ 
                        int id;
                        String username;
                        Account user;
                        System.out.println("What is the ID of the group?");
                        id = sc.nextInt();
                        sc.nextLine();
                        System.out.println("What is the username of the user to add?");
                        tempUsername = sc.nextLine();
                        user = findAccount(tempUsername);
                     //Group g = findGroup(id);
                        findGroup(id).addUser(user); // Group needs addUser 
                     }			
                  }
                  // view calendar
                  else if (choice == 3){ 
                  //System.out.println(((NormalUser)mainAccount).getCalendar()); // NormalUser needs getCalendar
                     viewCalendar();
                  }
                  // edit calendar
                  else if (choice == 4){
                     int dayChoice;
                     String timeChoice;
                     boolean availability;
                     System.out.println("What day of the week do you want to edit Sunday = 0 to Saturday = 6");
                     dayChoice = sc.nextInt();
                     sc.nextLine();
                     System.out.println("What time slot do you want to change? In format start time-end time, integers only (0-23 hours)");
                     timeChoice = sc.nextLine();
                     System.out.println("Are you busy or free in that time? Enter 1 for free, 2 for busy");
                     int temp = sc.nextInt();
                     sc.nextLine();
                     if (temp == 1) {
                        availability = true;
                     } else {
                        availability = false;
                     }
                     editCalendar(dayChoice, timeChoice, availability); // NormalUser needs editCalendar
                  }
                  // view events
                  else if (choice == 5){ 
                     viewEvent();
                  }
                  // edit events
                  else if (choice == 6){ 
                     int id;
                     Group g;
                     int startTime;
                     Event e;
                     System.out.println("Here are all the events you are in: ");
                     viewEvent();
                     System.out.println("What is the ID of the event you want to edit?");
                     id = sc.nextInt();
                     e = findEvent(id);
                     g = e.getGroup();
                     System.out.println("What do you want to change? 1-Time 2-Location");
                     int sel = sc.nextInt();
                     if(sel == 1){
                        System.out.println("Here are the best available times with your group.(1-Best to 7-Worst)");
                        for(int i = 0; i < 7; i++){
                           System.out.println(i+1+ ". "+ (g.findBestTimeSlots()[i]));
                        }
                        System.out.println("Enter in the time slot you want to change to: ");
                        int timechoice = sc.nextInt();
                        String time = g.findBestTimeSlots()[timechoice - 1];
                        findEvent(id).setDate(time);
                     }else if (sel ==2){
                        System.out.println("Here are all the blocks available: ");
                        viewAttractions();
                        System.out.println("What type of attraction do you want to go to? 1. Restuarant 2. Retail 3. Entertainment");
                        int attractionChoice = sc.nextInt();
                        sc.nextLine();	   
                        findEvent(id).changeAttraction(attractionChoice);
                     
                     }else{
                        System.out.println("Incorrect input");
                     
                     }
                  /////////////////////////////////////////////////////////////
                  }
                  // view map
                  else if (choice == 7){ 
                     System.out.println(charMap);
                  } 
                  // create group
                  else if (choice == 8){
                     int numUser;
                     Account [] groupMembers;
                     String[] usernames;
                     System.out.println("How many people are in the new group including you?");
                     numUser = sc.nextInt();
                     sc.nextLine();
                     usernames = new String[numUser];
                     groupMembers = new Account[numUser];
                     System.out.println("Here are all the Available users.");
                     viewAllUsers();
                     System.out.println("Please enter their usernames below. You do not have to enter yourself!\n");
                     System.out.println("User #: 1: "+mainAccount.getUsername());
                     groupMembers[0] = mainAccount;
                     for (int i = 1; i < numUser; i++){
                        System.out.print("User #: " + (i+1)+": ");
                        usernames[i] = sc.nextLine();
                        groupMembers[i] = findAccount(usernames[i]);
                     }
                     
                     groups[numGroup] = new Group(numGroup, numUser, groupMembers);
                     numGroup++;
                  }  
                  // create event
                  else if (choice == 9){
                  // id, numppl, numlocations, time, date, attraction, users
                     int groupID;
                     int attractionID;
                     String time;
                     String date;
                     Block a;
                     Group g;
                     Account[] members;
                     System.out.println("Here are all the blocks available: ");
                     viewAttractions();
                     System.out.println("Here are all the groups you are in: ");
                     viewGroup();
                     System.out.println("What is the ID of the group you want to create an event with?");
                     groupID = sc.nextInt();
                     System.out.println("What type of attraction do you want to go to? 1. Restuarant 2. Retail 3. Entertainment");
                     int attractionChoice = sc.nextInt();
                     sc.nextLine();
                     g = findGroup(groupID);
                     members = g.getGroupMembers();
                     System.out.println("Here are the best available times with your group.(1-Best to 7-Worst)");
                     for(int i = 0; i < 7; i++){
                        System.out.println(i+1+ ". "+ g.findBestTimeSlots()[i]);
                     }
                     System.out.println("Enter in the time slot you want: ");
                     int timechoice = sc.nextInt();
                     time = g.findBestTimeSlots()[timechoice - 1];
                  
                     events[numEvent] = new Event(numEvent+1, members.length,g, time, attractionChoice);
                     numEvent++;
                  }
                  else if(choice == 10){
                     System.out.println("Here are all the Groups you are in: ");
                     viewGroup();
                     System.out.println("What is the ID of the group you want to view calendar for?");
                     int id = sc.nextInt();
                     System.out.println(findGroup(id).getGroupCal());
                     sc.nextLine();
                  
                  }
                  else if (choice == 11){
                     System.out.println("Your balance is: "+((NormalUser)mainAccount).getBalance());
                  }
                  else if (choice == 12){
                     System.out.print("Enter amount to add to Account: ");
                     double bal = sc.nextDouble();
                     if(((NormalUser)mainAccount).updateBalance(bal)){
                        System.out.println("Successful");
                     }
                     else{
                        System.out.println("Unsuccessful");
                     }
                  }
                  else if(choice == 13){
                     System.out.println("Enter the event ID to delete: ");
                     int tID = sc.nextInt();
                     deleteEvent(findEvent(tID));
                         
                  }
                  else if(choice == 14){
                     editPreferredAttraction();
                  }
                  else if(choice == 15){
                     editInfo();
                  }
                  else if(choice == 16){
                     System.out.println("Press 1 if you want to delete your account");
                     choice = sc.nextInt();
                     if (choice == 1){
                        deleteAccount(mainAccount);
                     }
                  }
                  else if(choice == 17){
                     viewEvent();
                     int inputChoice;
                     System.out.println("Enter ID of the event you want to find route to.");
                     inputChoice = sc.nextInt();
                     sc.nextLine();
                     System.out.println(findEvent(inputChoice));
                     viewRoute(findHouse(((NormalUser)mainAccount).getX(), ((NormalUser)mainAccount).getY()),findEvent(	inputChoice));
                  }
                  else if(choice == 18){
                     ((NormalUser)mainAccount).viewAllInfo();
                  }
                  else if(choice == 19){
                     saveEverything();
                     loggedOut = true;
                     System.out.println("You are logged out.");
                     homeScreen();
                  }
                  else{
                     System.out.println("Invalid input, try again!");
                  }
               }
            }
            repeat  = false;
         }catch (InputMismatchException ime) {
            System.out.println("oof.");
            String flush = sc.next();
         } catch (NumberFormatException nfe) {
            System.out.println("noo. ");
         } catch (Exception e) {
            System.out.println("yikes."); 
         }
      }
   }
   
  // Create the map and initializes all blocks
   private void setBlockMap(){
      mapx = map.length;
      mapy = map.length;
      mapBlock = new Block[mapx][mapy];
      for(int i=0; i < mapx; i++){
         for(int j = 0; j<mapy; j++){
            mapBlock[i][j] = null;
         }
      }
      for(int i = 0; i < blocks.length; i++){
         if(blocks[i] != null){
            mapBlock[blocks[i].getX()-1][blocks[i].getY()-1] = blocks[i];
         }
      }
   
      for(int i=0; i < mapx; i++){
         for(int j = 0; j<mapy; j++){
            if(mapBlock[i][j] == null){
               mapBlock[i][j] = new Nature(i,j);
            }
         }
      }
   }
  
   // Reads in calendars from the text file
   private void readInCalendar (String calendarFile) {
      String temp = "";
      String normUser;
      int num =0;
      try {
         BufferedReader in = new BufferedReader(new FileReader(calendarFile));
         numCal = Integer.parseInt(in.readLine());
         String input = in.readLine();
         while (input != null) {
            normUser = input;
            for (int i = 0; i < Calendar.NUM_DAYS; i ++) {
               temp += in.readLine();
               temp += "\n";
            }
            calendars[num] = new Calendar(normUser, temp);
            num ++;
            input = in.readLine();
            temp = "";
         }
         in.close();
      } catch (IOException iox) {
         System.out.println("Error reading file.");
      }
   }
   
  // Reads in blocks from the text file for map
   private void readInBlock(String blockFile){
      int attractionsSet = 0;
      try{
         BufferedReader in = new BufferedReader(new FileReader(blockFile));
         numBlock = Integer.parseInt(in.readLine());
         for(int i = 0; i < MAXBLOCKS; i++){
            blocks[i] = null;
         }
         for(int b = 0; b < numBlock; b++){
            String input = in.readLine();
            if(input.equals("H")|| input.equals("R")||input.equals("C")||input.equals("E")){
               String name = in.readLine();///////get name              
               String address = in.readLine();
               String phone = in.readLine();
               String[] coordinates = in.readLine().split(",");
               int x = Integer.parseInt(coordinates[0]);
               int y = Integer.parseInt(coordinates[1]); 
              
               if(input.equals("H")){ //houses  
                  blocks[b] = new House(address,name, phone, x, y);
               }
               else {
                  String [] price = in.readLine().split("-");
                  double min = Double.parseDouble(price[0]);
                  double max = Double.parseDouble(price[1]);
                  String[] time = in.readLine().split("-");
                  int open = Integer.parseInt(time[0]);
                  int close = Integer.parseInt(time[1]);
               
                  if(input.equals("R")){ //food/resturant
                     blocks[b] = new Food(numAttraction, phone, name, address, x, y, min,max,open,close);
                     listOfAttractions[numAttraction] = ((Attractions)blocks[b]);
                     numAttraction ++;
                  } 
                  else if(input.equals("C")){ //clothing
                     blocks[b] = new Retail (numAttraction,phone, name, address, x, y, min,max,open,close);
                     listOfAttractions[numAttraction] = ((Attractions)blocks[b]);
                     numAttraction ++;
                  }
                  else if(input.equals("E")){ //entertainment
                     blocks[b] = new Entertainment(numAttraction ,phone,name,address, x, y, min,max,open,close); //add name and address
                     listOfAttractions[numAttraction] = ((Attractions)blocks[b]);
                     numAttraction ++;
                  }
               }                 
            }
            else{ //streets
               String name = in.readLine();///////get name
               String[] coordinates = in.readLine().split(",");
               int x = Integer.parseInt(coordinates[0]);
               int y = Integer.parseInt(coordinates[1]); 
               coordinates = in.readLine().split(",");
               int xend = Integer.parseInt(coordinates[0]);
               int yend = Integer.parseInt(coordinates[1]); 
               blocks[b] = new Street(b,x, y,xend, yend,name, Integer.parseInt(input));
            
            }    
         
         }
         in.close();
      } 
      catch(IOException iox){
         System.out.println("Error reading file");
      }
   }
  
	 // Reads in all groups from text file 
   private void readInGroup(String groupFile){
      try{
         BufferedReader in = new BufferedReader(new FileReader(groupFile));
         numGroup = Integer.parseInt(in.readLine());
         groups = new Group[MAX_GROUPS];
         
         for(int g = 0; g < numGroup; g++){
            int id = Integer.parseInt(in.readLine());
            int numUsers = Integer.parseInt(in.readLine());
            Account[] users = new Account[numUsers];
            for(int i = 0; i < numUsers; i++){
               String input = in.readLine();
               for(int j = 0; j < numAccount; j++){
                  if(input.equals(accounts[j].getUsername())){
                     users[i] = accounts[j];
                     
                  }
               }  
            }
            groups[g] = new Group(id,numUsers,users); //userID is []
           //    System.out.println(groups[g]);    
         }
         in.close();
      }catch(IOException iox){
         System.out.println("Error reading file");
      }
   
   }   
   private void setMap(String mapFile){
      charMap = new Map(mapFile);
      map = charMap.getMap();
   }
		
  // Deletes the users account if they wish to do so and shifts array up
   private void deleteAccount (Account acc) {
      boolean shift  = false;
      int start = numAccount - 1;
      for (int i = 0; i < numAccount; i++) {        
         if (acc.equals(accounts[i])) {
            accounts[i] = null;
            numAccount--;
            shift = true;
            start = i;
         }
      }
      if (shift) {
         for(int i = start; i < numEvent-1; i ++){
            accounts[i] = accounts[i+1];
         }
         System.out.println("Successfully Deleted");
      } else {
         System.out.println("Unsuccessfully Deleted");
      }
      loggedOut = true;
   }
   
  // Deletes the event if the user wishes to do so, shifts array of events up
   private boolean deleteEvent (Event eve) {
      boolean shift  = false;
      int start = numEvent-1;
      for (int i = 0; i < numEvent; i++) {        
         if (eve.equals(events[i])) {
            events[i] = null;
            numEvent--;
            shift = true;
            start = i;
         }
      }
      if (shift) {
         for(int i = start; i < numEvent-1; i ++){
            events[i] = events[i+1];
            events[i].setEventID(i+1);
         }
         System.out.println("Successfully Deleted");
      }else{
         System.out.println("Unsuccessfully Deleted");
      }
   
      return shift;
   }


   // Reads in all the accounts from text file and initializes them in array
   private boolean readInAccount(String filename){
      try{
         BufferedReader in = new BufferedReader(new FileReader(filename));
         numAccount = Integer.parseInt(in.readLine());
         //System.out.println(numAccount);//////////////////////
         accounts = new Account [MAX_ACCOUNTS];
         for(int i  = 0; i< MAX_ACCOUNTS; i++){
            accounts[i]=null;
         }
         for(int i = 0; i < numAccount; i++){
            if(i > MAX_ACCOUNTS){
               return false;
            }
            else{
               String userChar = in.readLine();
               if(userChar.equals("n")){
                  String username = in.readLine();
                  String phoneNum = in.readLine();
                  String name = in.readLine();
                  String password = in.readLine();
                  String address = in.readLine();
                  String[] preferredNames = new String[3];
                  Block[] preferredBlocks = new Block[3];
                  double balance = Double.parseDouble(in.readLine());
                  String credNum = in.readLine();
                  String expDate = in.readLine();
                  int cvvNum = Integer.parseInt(in.readLine());
                  int weekBud = Integer.parseInt(in.readLine());
                  Money bal = new Money(balance, credNum, expDate, cvvNum, weekBud);
                  int x =  Integer.parseInt(in.readLine());
                  int y =  Integer.parseInt(in.readLine());
                  for(int p = 0; p < 3; p++){
                     String input = in.readLine(); 
                    // System.out.println(input);
                     preferredBlocks[p] = findAttraction(input);  
                     //System.out.println((Attractions)preferredBlocks[p]);
                  }
                  accounts[i] = new NormalUser(username, phoneNum, name, password, bal, address, x, y, preferredBlocks, getCal(username));
                  //System.out.println("work");
               }
               else if(userChar.equals("a")){
               
                  String username = in.readLine();
               //   System.out.println(username);
                  String name = in.readLine();
                  String phoneNum = in.readLine();
                  String password = in.readLine();
                 // System.out.println(password);
                  accounts[i] = new Administrator(username,name,phoneNum,password);
               }
            
            }
         } 
         in.close();	
      }
      catch(IOException iox){
         System.out.println("Error reading file");
      }
      return true;
   }
	 
   // Returns the calendar of a given user
   private Calendar getCal(String username){
      for(int i = 0; i< calendars.length; i++){
         if ((calendars[i].getUsername()).equals(username)) {
            return calendars[i];
         }
      }
      return null;
   }
  
   // Reads in all events from events file and initialze events array
   private void readInEvent(String eventFile){
      for(int i = 0; i < MAXEVENTS; i++){
         events[i] = null;
      }
      try{
         BufferedReader in = new BufferedReader(new FileReader(eventFile));
         numEvent = Integer.parseInt(in.readLine());
         
         Group group = null;
         for(int e = 0; e < numEvent; e++){
            int eventID = Integer.parseInt(in.readLine());
            int numPeople = Integer.parseInt(in.readLine());
            int groupID = Integer.parseInt(in.readLine());
            for(int g = 0; g < numGroup; g++){
               if(groups[g].getID()== groupID){
                  group = groups[g];
               }  
            }
            String date = in.readLine();
            String address = in.readLine();
            int xCord = Integer.parseInt(in.readLine());
            int yCord = Integer.parseInt(in.readLine());
            events[e] = new Event(eventID, numPeople, group, date, typeOfBlock(xCord,yCord));
         }
         in.close();
      } catch(IOException iox){
         System.out.println("Error reading file.");
      } 
      
     
   
   }



   // Creates a new account and adds it to array
   private void createAccount(){
      boolean work =false;
      boolean threshold =false;
      while(!work){
         try { 
            threshold =false;
          //  sc.nextLine();
            System.out.println("Registration Screen");
            String username = "";
            String password, name, phoneNumber, address;
            int x, y;
            double bal;
            String credNum;
            String expDate; 
            int cvvNum;
            int weekBud;
            Attractions preferred;
            boolean notTaken = false;
            while (!notTaken || username.equals("")) {
               System.out.println("Enter your username");
               username = sc.nextLine();
               notTaken = true;
               for (int i = 0; i < numAccount; i ++) {
                  if (username.equals(accounts[i].getUsername())) {
                     System.out.println("Username taken. Try again.");
                     notTaken = false;
                  } 
               } 
            }
            System.out.println("Enter your password");
            password = sc.nextLine();
            System.out.println("Enter your name");
            name = sc.nextLine();
            System.out.println("Enter your phone number");
            phoneNumber = sc.nextLine();
            System.out.println("Enter your address");
            address = sc.nextLine();
            System.out.println("What is your credit card number?");
            credNum = sc.nextLine();
            System.out.println("What is your CVV?");
            cvvNum = sc.nextInt();
            System.out.println("What is your weekly budget?");
            weekBud = sc.nextInt();
            System.out.println("What is your balance?");
            bal = sc.nextDouble();
            sc.nextLine();
            System.out.println("What is your expiry date?");
            expDate = sc.nextLine();
         
            Money money = new Money(bal, credNum, expDate, cvvNum, weekBud);
            numMoney++;
         //             sc.nextLine();
            viewAttractions();
            System.out.println("What is your preferred attraction ID?");
            int[] preferredID = new int[3];
            Block[] preferredAttractions = new Block[3];
            Block temporary;
            boolean correctAttraction = false;
            while (!correctAttraction) {
               System.out.println("Enter your food location.");
               preferredID[0] = sc.nextInt(); 
               temporary = findAttraction(preferredID[0]);
               if (temporary instanceof Food) {
                  preferredAttractions[0] = findAttraction(preferredID[0]);
                  correctAttraction = true;
               } else {
                  System.out.println("Not a food location! Try again!");
               }
            }
            correctAttraction = false;
            while (!correctAttraction) {
               System.out.println("Enter your clothing location.");
               preferredID[1] = sc.nextInt(); 
               temporary = findAttraction(preferredID[1]);
               if (temporary instanceof Retail) {
                  preferredAttractions[1] = findAttraction(preferredID[1]);
                  correctAttraction = true;
               } else {
                  System.out.println("Not a clothing location! Try again!");
               }
            }
            correctAttraction = false;
            while (!correctAttraction) {
               System.out.println("Enter your entertainment attraction.");
               preferredID[2] = sc.nextInt(); 
               temporary = findAttraction(preferredID[2]);
               if (temporary instanceof Entertainment) {
                  preferredAttractions[2] = findAttraction(preferredID[2]);
                  correctAttraction = true;
               } else {
                  System.out.println("Not an entertainment attraction! Try again!");
               }
            }
            sc.nextLine();
            System.out.println("What time are you available from Sunday to Monday? \n(Input in format 'hour hour' and a space between all new time intervals:");
            String newCal = "";
            for (int i = 0; i < Calendar.NUM_DAYS; i ++) {
               newCal += i + " ";
               System.out.println("" + (i + 1) + ": ");
               newCal += sc.nextLine().trim();
               newCal += "\n";
            }
            int [] coordinates  =  makeAvailHouse();
            calendars[numAccount] = new Calendar(username, newCal);
            accounts[numAccount] = new NormalUser(username, phoneNumber, name, password, money, address,coordinates[0],coordinates[1], preferredAttractions,calendars[numAccount]);/////////////////////////
            blocks[numBlock] = new House(address, name, phoneNumber, coordinates[0], coordinates[1] );
            threshold = true;
            
            numBlock++;
            numAccount++;
            setBlockMap();
            System.out.println("Registration completed");
            work = true;
            saveEverything();
            initalize();
         } catch (InputMismatchException ime) {
            System.out.println("Exception occurred. Please input information in correct format.Press Enter if necessary.");
            if (!threshold){
               String flush = sc.next();
            }
         } catch (NumberFormatException nfe) {
            System.out.println("Exception occurred. Please input information in correct format. ");
         } catch (Exception e) {
            System.out.println("Exception occurred. Please input information in correct format."); 
         }
                 
      }
   }
   
   private int [] makeAvailHouse(){
      int [] list  = new int [2];
         for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map.length; j++){
               if(map[i][j] == 'N'){
               map[i][j] = 'H';
                  list[0] = i+1;
                  list[1] = j+1;
                  return list;
               }
            }
         }     
      
      return null;
   }

   // Method used for user to log in, determines whether user is admin or not
   private boolean login(){
      boolean repeat  =true;
      while(repeat){
         try{ 
            System.out.println("\nLogin Screen");
            System.out.println("Enter in your username: ");
            String inputUser = sc.nextLine();
         //username ="readforfun";
            System.out.println("Enter in your password: ");
            String password = sc.nextLine();
         //String password  = "compengboi";
            for(int i = 0; i < numAccount; i++){
            //  System.out.println(accounts[i].getUsername());
            //  System.out.println(accounts[i].getPassword());
               if(accounts[i].equals(inputUser, password)){
                  mainAccount = accounts[i];
                  username = inputUser;
                  if(mainAccount instanceof Administrator){
                     System.out.println();
                     admin = true;
                  }
                  loggedOut = false;
                  mainMenu();
                  return true;
               
               }        
            }
            System.out.println("Wrong password or username");
            repeat  =false;
            return false;
         }catch (InputMismatchException ime) {
            System.out.println("Exception occurred. Please input information in correct format.");
            String flush = sc.next();
         } catch (NumberFormatException nfe) {
            System.out.println("Exception occurred. Please input information in correct format. ");
         } catch (Exception e) {
            System.out.println("Exception occurred. Please input information in correct format."); 
         }
      }
      return false;
   }   
   
   // Can view the calendar of the logged in user
   private void viewCalendar () {
      for (int i = 0; i < numAccount; i ++) {
         if ((calendars[i].getUsername()).equals(username)) {
            System.out.print(calendars[i]);
         }
      }
   }
   
// User can view all the groups they are in
   private void viewGroup(){
      for (int i = 0; i < numGroup; i++){
         if (groups[i].userInGroup(mainAccount)){ // Group needs userInGroup method
            System.out.println(groups[i]);
         }	
      }
   }

// User can view all the events they are in
   private void viewEvent(){
      for (int i = 0; i < numEvent; i++){
         if (events[i].userInEvent(mainAccount)){ // Event needs userInEvent method
            System.out.println(events[i]);
         }	
      }
   }

// View all blocks available
   private void viewAttractions(){
      for (int i = 0; i < numAttraction; i++){
         System.out.println(""+i+". "+ (Attractions)listOfAttractions[i]);
      }
   }
   
   // Checks if the given username exists in database
   private boolean validUser (String userN) {
      boolean valid = true;
      for (int i = 0; i < numAccount; i ++) {
         if (!(accounts[i].validUsername(userN))) {
            valid = false;
         }
      }
      return valid;
   }
   
   // Method the user can use to edit the information in their profile
   private void editInfo () {
      boolean repeat  = true;
      while(repeat){
         try{
            System.out.println("What would you like to edit?: ");
            System.out.println("1. Name");
            System.out.println("2. Phone Number");
            System.out.println("3. Username");
            System.out.println("4. Password");
            System.out.println("5. Address");
            int edit = sc.nextInt();
            String edited;
            sc.nextLine();
            if (edit == 1) {
               System.out.println("Enter new name: ");
               edited = sc.nextLine();
               mainAccount.setName(edited);
            } else if (edit == 2) {
               System.out.println("Enter new phone number: ");
               edited = sc.nextLine();
               mainAccount.setPhoneNumber(edited);
            } else if (edit == 3) {
               System.out.println("Enter new username: ");
               edited = sc.nextLine();
               while (!(validUser(edited))) {
                  System.out.println("Username taken.");
                  System.out.println("Enter new username: ");
                  edited = sc.nextLine();
               }
               mainAccount.setUsername(edited);
            } else if (edit == 4) {
               System.out.println("Enter new password: ");
               edited = sc.nextLine();
               mainAccount.setPassword(edited);
            } else if (edit == 5) {
               System.out.println("Enter new address: ");
               edited = sc.nextLine();
               ((NormalUser)mainAccount).setAddress(edited);
            }
         
            repeat = false;
         }catch (InputMismatchException ime) {
            System.out.println("Exception occurred. Please input information in correct format.");
            String flush = sc.next();
         } catch (NumberFormatException nfe) {
            System.out.println("Exception occurred. Please input information in correct format. ");
         } catch (NullPointerException npe) {
            System.out.println("Exception occurred. Please input information in correct format.");
         } catch (Exception e) {
            System.out.println("Exception occurred. Please input information in correct format."); 
         }
      }
   }
   
   // Method called when user wants to edit the calendar
   private void editCalendar(int day, String times, boolean avail){
      String [] time = times.split("-");
      int start = Integer.parseInt(time[0]);
      int end = Integer.parseInt(time[1]);
      for (int i = 0; i < numAccount; i ++) {
         if ((calendars[i].getUsername()).equals(username)) {
            calendars[i].editCalendar(day, start, end, avail);
         }
      }
         // calendar.editCalendar(this, day, start, end, avail);
   }
   
   // User can edit their preferred attraction
   private void editPreferredAttraction () {
      boolean repeat  = true;
      while(repeat){
         try{
            int id = 0;
            boolean valid = false;
            if (!valid) {
               viewAttractions();
               System.out.println("Which type of preferred attraction do you want to change? 1-Restuarant 2-Retail 3-Entertainement");
               int sel = sc.nextInt();
               if(sel == 1){
                  Block temporary;
                  boolean correctAttraction = false;
                  while (!correctAttraction) {
                     System.out.println("Enter your new food location.");
                     id = sc.nextInt(); 
                     temporary = findAttraction(id);
                     if (temporary instanceof Food) {
                        correctAttraction = true;
                     } else {
                        System.out.println("Not a food location! Try again!");
                     }
                     ((NormalUser)mainAccount).editPreferredLocation(sel, findAttraction(id));
                  }
               } else if (sel == 2) {
                  Block temporary;
                  boolean correctAttraction = false;
                  while (!correctAttraction) {
                     System.out.println("Enter your new retail location.");
                     id = sc.nextInt(); 
                     temporary = findAttraction(id);
                     if (temporary instanceof Retail) {
                        correctAttraction = true;
                     } else {
                        System.out.println("Not a retail location! Try again!");
                     }
                     ((NormalUser)mainAccount).editPreferredLocation(sel, findAttraction(id));
                  }
               } else {
                  Block temporary;
                  boolean correctAttraction = false;
                  while (!correctAttraction) {
                     System.out.println("Enter your new entertainment location.");
                     id = sc.nextInt(); 
                     temporary = findAttraction(id);
                     if (temporary instanceof Entertainment) {
                        correctAttraction = true;
                     } else {
                        System.out.println("Not an entertainment location! Try again!");
                     }
                  }
                  ((NormalUser)mainAccount).editPreferredLocation(sel, findAttraction(id));
               
                  // System.out.println("Choose your new preferred food location by ID");
               //                   id = sc.nextInt();
                  
               }
               valid = true;
            }
            else {
               valid = false;
            }
            sc.nextLine();
            repeat = false;
         } catch (InputMismatchException ime) {
            System.out.println("Exception occurred. Please input information in correct format.");
            String flush = sc.next();
         } catch (NumberFormatException nfe) {
            System.out.println("Exception occurred. Please input information in correct format. ");
         } catch (Exception e) {
            System.out.println("Exception occurred. Please input information in correct format."); 
         }
      }
   }

// Returns an account with a given username
   private Account findAccount(String username){
      Account a = new Account();
      boolean found = false;
      for (int i = 0; i < numAccount; i++){
         if (accounts[i].getUsername().equals(username)){ // nomral user needs equals
            a = accounts[i];
            found = true;
         }
      }
      return a;
   }

// Returns a group with a given id
   private Group findGroup(int id){
      Group g = new Group();
      boolean found = false;
      for (int i = 0; i < numGroup; i++){
         if (groups[i].getID() == id){
            g = groups[i];
            found = true;
         }
      }
      return g;
   }

// Returns an event with a given id
   private Event findEvent(int id){
      Event e = new Event();
      boolean found = false;
      for (int i = 0; i < numEvent; i++){
         if (events[i].getEventID() == id){
            e = events[i];
            found = true;
         }
      }
      return e;
   }

// Returns an attraction with a given id
   private Block findAttraction(int id){
      Block a = null;
      for (int i = 0; i < numAttraction; i ++) {
         if (((Attractions)listOfAttractions[i]).getID() == id) {
            a = listOfAttractions[id];
         }
      }
      return a;
   }
   
   // Returns attraction with a given name
   private Block findAttraction(String name){
      Block a = null;
      
      for (int i = 0; i < numAttraction; i ++) {
         if (((Attractions)listOfAttractions[i]).equals(name)) {
            a = listOfAttractions[i];
         }
      }
      return a;
   
   }
	 
   // Method finds a house at a specified coordinate
   private Block findHouse(int x, int y){
      Block a = null;
      boolean found = false;
      for (int i = 0; i < numBlock; i ++) {
         if (blocks[i].getX() == x &&blocks[i].getY() == y) {
            a = blocks[i];
         }
      }
      return a;
   }
	

// Attraction editing methods
// All of these need new methods in attraction class 
   private void editName(int id){
      boolean repeat  = true;
      while(repeat){
         try {
            String newName;
            System.out.println("What is the new name?");
            newName = sc.nextLine();
            ((Attractions)findAttraction(id)).editAttractionName(newName);
            repeat = false;
         }catch (InputMismatchException ime) {
            String flush = sc.next();
            System.out.println("Exception occurred. Please input information in correct format.");
         } catch (NumberFormatException nfe) {
            System.out.println("Exception occurred. Please input information in correct format. ");
         } catch (Exception e) {
            System.out.println("Exception occurred. Please input information in correct format."); 
         }
      }
   }
	
   // Admin can edit the phone number of an Attraction class 
   private void editPhoneNumber(int id){
      boolean repeat  = true;
      while(repeat){
         try{
            String newPhoneNumber;
            System.out.println("What is the new phone number?");
            newPhoneNumber = sc.nextLine();
            ((Attractions)findAttraction(id)).editAttractionPhoneNumber(newPhoneNumber);
            repeat = false;
         } catch (InputMismatchException ime) {
            String flush = sc.next();
            System.out.println("Exception occurred. Please input information in correct format.");
         } catch (NumberFormatException nfe) {
            System.out.println("Exception occurred. Please input information in correct format. ");
         } catch (Exception e) {
            System.out.println("Exception occurred. Please input information in correct format."); 
         }
      }
   }
   
   // Admin can edit the max price of an attraction
   private void editMaxPrice(int id){
      boolean repeat  = true;
      while(repeat){    
         try {
            double newMaxPrice;
            System.out.println("What is the new max price?");
            newMaxPrice = sc.nextDouble();
            ((Attractions)findAttraction(id)).editAttractionMaxPrice(newMaxPrice);
            repeat = false;
         } catch (InputMismatchException ime) {
            String flush = sc.next();
            System.out.println("Exception occurred. Please input information in correct format.");
         } catch (NumberFormatException nfe) {
            System.out.println("Exception occurred. Please input information in correct format. ");
         } catch (Exception e) {
            System.out.println("Exception occurred. Please input information in correct format."); 
         }
      }
   }
	
   // Admin can edit the minimum price in an Attraction class
   private void editMinPrice(int id){
      boolean repeat  = true;
      while(repeat){
         try{
            double newMinPrice;
            System.out.println("What is the new min price?");
            newMinPrice = sc.nextDouble();
            ((Attractions)findAttraction(id)).editAttractionMaxPrice(newMinPrice);
            repeat = false;
         } catch (InputMismatchException ime) {
            String flush = sc.next();
            System.out.println("Exception occurred. Please input information in correct format.");
         } catch (NumberFormatException nfe) {
            System.out.println("Exception occurred. Please input information in correct format. ");
         }  catch (Exception e) {
            System.out.println("Exception occurred. Please input information in correct format."); 
         }
      }
   }
  
   // Admin can edit the address for an attraction
   private void editAddress(int id){ 
      boolean repeat  = true;
      while(repeat){
         try{
            String address;
            System.out.println("What is the new address");
            address = sc.nextLine();
            ((Attractions)findAttraction(id)).editAttractionAddress(address);
            repeat = false;
         } catch (InputMismatchException ime) {
            String flush = sc.next();
            System.out.println("Please enter an address.");
         } catch (NumberFormatException nfe) {
            System.out.println("Exception occurred. Please input information in correct format. ");
         } catch (Exception e) {
            System.out.println("Exception occurred. Please input information in correct format."); 
         }
      }
   }   
	
   // Determines the type of block at the specified coordinate
   private Block typeOfBlock(int x, int y){
      for(int i = 0; i < numBlock; i++){
         if(blocks[i].getY() == y && blocks[i].getX()==x){
            return blocks[i];
         }
      
      }
      return null;
   }  
   
   private void viewAllUsers(){
      for(int i = 0; i< numAccount; i++){
         if(accounts[i] instanceof NormalUser && accounts[i]!= mainAccount){
            System.out.println(accounts[i]);
         }
      }
   }
   
   //////////////////////////////////////////////////////////////////////////HOME
   // Initializes all array objects
   private void initalize(){
      numAttraction =0;
      numAccount = 0;
      numGroup = 0;
      numEvent =0;
      numBlock = 0;
      numCal = 0;
      readInCalendar(calendarFile);
      readInBlock(blockFile);
      readInAccount(accountFile);
      setMap(mapFile);
      setBlockMap();
      readInGroup(groupFile);
      readInEvent(eventFile);
   }
   
   // Saves all the arrays onto text files
   private void saveEverything(){
      saveAllAccounts(accountFile);
      saveAllEvents(eventFile);
      saveAllGroups(groupFile);
      saveAllCalendars(calendarFile);
      updateMapToFile(mapFile);
      saveAllBlocks(blockFile);
   }
	
   private void saveEverythingAdmin(){
      saveAllAccounts(accountFile);
      saveAllBlocks(blockFile);
   }
	  
   // Shows the home screen for when the program is first run. 
   // If the user decides to log in they are directed to the main menu. 
   // If the user decides to create an account they are directed to the create account method.
   public void homeScreen(){
      initalize();
      
      boolean repeat  = true;
      while(repeat){ 
         int homeInput = 0;
         try{         
            System.out.println("1.Login");
            System.out.println("2.Register");
            homeInput = sc.nextInt();
            
            if (homeInput == 1) {
               sc.nextLine();
               if(!login()){              
                  System.out.println("Try again");
               }else{
                  repeat = false; 
               }
            } else if (homeInput == 2) {
               sc.nextLine();
               createAccount();
               login();
               repeat = false;
            }
            
         } catch (InputMismatchException ime) {
            System.out.println("Exception occurred. Please input information in correct format."); 
            String flush = sc.next();
         } 
      }
   }
	 
		// User can view the route to the event they want
   private void viewRoute(Block house, Event eve){
      Route route = new Route(house, eve, charMap);
      System.out.println(route);
      System.out.println(route.calculateTime());
   
   }
   
   
   
   
   
   
   
   
   
   
   
   //////////////////////////////////////////////////////////////////////////////////////////Saving
	// Saves a list of all users to a text file
	 // Saves all accounts in the array to the accounts text file
   private void saveAllAccounts(String file){
      try{
         BufferedWriter out = new BufferedWriter(new FileWriter(file));
         out.write(""+numAccount);
         out.newLine();
         for(int i = 0; i < numAccount; i++){
            if(accounts[i] instanceof Administrator){
               out.write("a");
               out.newLine();
            }else{
               out.write("n");
               out.newLine();
            }
            out.write(accounts[i].getUsername());
            out.newLine();
            out.write(accounts[i].getPhoneNumber());
            out.newLine();
            out.write(accounts[i].getName());
            out.newLine();
            out.write(accounts[i].getPassword());
            out.newLine();
            if(accounts[i] instanceof NormalUser){
               out.write(((NormalUser)accounts[i]).getAddress());
               out.newLine();
               out.write(""+((NormalUser)accounts[i]).getMoney().getBalance());
               out.newLine();
               out.write(""+((NormalUser)accounts[i]).getMoney().getCredNum());
               out.newLine();
               out.write(((NormalUser)accounts[i]).getMoney().getExpDate());
               out.newLine();
               out.write(""+((NormalUser)accounts[i]).getMoney().getCVV());
               out.newLine();
               out.write(""+((NormalUser)accounts[i]).getMoney().getWeeklyBudget());
               out.newLine();
               out.write(""+((NormalUser)accounts[i]).getX());
               out.newLine();
               out.write(""+((NormalUser)accounts[i]).getY());
               out.newLine();
               for(int p = 0; p < 3; p++){
                  out.write(""+((NormalUser)accounts[i]).getPreferredAttraction(p+1)); 
                  out.newLine();
               }
            }
         } 
         out.close();	
      }
      catch(IOException iox){
         System.out.println("Error finding file.");
      }
   }
   
	// Saves a list of events to a text file
   // Saves all events to the specified file
   private void saveAllEvents(String file){
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(file));
         out.write(""+numEvent);
         out.newLine();
         
         for(int i=0; i< numEvent;i++){
            out.write("" + events[i].getEventID());
            out.newLine();
            out.write("" + events[i].getNumPeople());
            out.newLine();
            out.write("" + events[i].getGroupID());
            out.newLine();
            out.write(events[i].getDate());
            out.newLine();
            out.write(events[i].getBlockName());
            out.newLine();
            out.write(""+events[i].getBlock().getX());
            out.newLine();
            out.write(""+events[i].getBlock().getY());
            out.newLine();
         }
         out.close();
      } catch (IOException iox) {
         System.out.println("Error reading file.");
      }
   
   }
   
   private void saveAllBlocks (String file) {
      try{
         BufferedWriter out = new BufferedWriter(new FileWriter(file));
         out.write("" + numBlock);
         out.newLine();
         for(int b = 0; b < numBlock; b++){     
            if (blocks[b] instanceof House) {
               out.write("H");
               out.newLine();
               out.write(((House)blocks[b]).getName());
               out.newLine();
               out.write(blocks[b].getAddress());
               out.newLine();
               out.write(((House)blocks[b]).getPhoneNum());
               out.newLine();
               out.write("" + blocks[b].getX() + "," + blocks[b].getY());
               out.newLine();
            } else if (blocks[b] instanceof Food) {
               out.write("R");
               out.newLine();
               out.write(((Attractions)blocks[b]).getName());
               out.newLine();
               out.write(blocks[b].getAddress());
               out.newLine();
               out.write(((Attractions)blocks[b]).getPhoneNumber());
               out.newLine();
               out.write("" + blocks[b].getX() + "," + blocks[b].getY());
               out.newLine();
               out.write("" + ((Attractions)blocks[b]).getMinPrice() + "-" + ((Attractions)blocks[b]).getMaxPrice());
               out.newLine();
               out.write("" + ((Attractions)blocks[b]).getStartHour() + "-" + ((Attractions)blocks[b]).getEndHour());
               out.newLine();
               //if(input.equals("H")){ //houses  
               //blocks[b] = new House(address, phone, x, y);
            } else if (blocks[b] instanceof Entertainment){
               out.write("E");
               out.newLine();
               out.write(((Attractions)blocks[b]).getName());
               out.newLine();
               out.write(blocks[b].getAddress());
               out.newLine();
               out.write(((Attractions)blocks[b]).getPhoneNumber());
               out.newLine();
               out.write("" + blocks[b].getX() + "," + blocks[b].getY());
               out.newLine();
               out.write("" + ((Attractions)blocks[b]).getMinPrice() + "-" + ((Attractions)blocks[b]).getMaxPrice());
               out.newLine();
               out.write("" + ((Attractions)blocks[b]).getStartHour() + "-" + ((Attractions)blocks[b]).getEndHour());
               out.newLine();
            //                   String [] price = in.readLine().split("-");
            //                   double min = Double.parseDouble(price[0]);
            //                   double max = Double.parseDouble(price[1]);
            //                   String[] time = in.readLine().split("-");
            //                   int open = Integer.parseInt(time[0]);
            //                   int close = Integer.parseInt(time[1]);
               
            } else if (blocks[b] instanceof Retail) {
               out.write("C");
               out.newLine();
               out.write(((Attractions)blocks[b]).getName());
               out.newLine();
               out.write(blocks[b].getAddress());
               out.newLine();
               out.write(((Attractions)blocks[b]).getPhoneNumber());
               out.newLine();
               out.write("" + blocks[b].getX() + "," + blocks[b].getY());
               out.newLine();
               out.write("" + ((Attractions)blocks[b]).getMinPrice() + "-" + ((Attractions)blocks[b]).getMaxPrice());
               out.newLine();
               out.write("" + ((Attractions)blocks[b]).getStartHour() + "-" + ((Attractions)blocks[b]).getEndHour());
               out.newLine();
            } else { //streets
               out.write("" + ((Street)blocks[b]).getNum());
               out.newLine();
               out.write(((Street)blocks[b]).getName());
               out.newLine();
               out.write("" + blocks[b].getX() + "," + blocks[b].getY());
               out.newLine();
               out.write("" + ((Street)blocks[b]).getXEnd() + "," + ((Street)blocks[b]).getYEnd());
               out.newLine();
            } 
         } 
         out.close();
      } catch(IOException iox){
         System.out.println("Error writing to file");
      }
   }
      
   private void saveAllGroups (String file) {
      try {
         BufferedWriter out = new BufferedWriter (new FileWriter(file));
         out.write("" + numGroup);
         out.newLine();
         for (int i = 0; i < numGroup; i ++) {
            out.write("" + groups[i].getID());
            out.newLine();
            out.write("" + groups[i].getNumberOfMembers());
            out.newLine();
            NormalUser [] listUser = groups[i].getGroupMembers();
            for (int j = 0; j < groups[i].getNumberOfMembers(); j ++) {
               out.write(listUser[j].getUsername());
               out.newLine();
            }
         }
         out.close();
      } catch (IOException iox) {
         System.out.println("NO");
      }
   }
   
   private void saveAllCalendars (String file) {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(file));
         out.write("" + numAccount);
         out.newLine();
         for (int i = 0; i < numAccount; i ++) {
            String temp = calendars[i].updateTextToFile();
            String [] cal = temp.split("\n");
            for (int j = 0; j < cal.length; j ++) {
               out.write(cal[j]);
               out.newLine();
            }
         }
         out.close();
      } catch (IOException iox) {
         System.out.println("NO");
      }
   }
   
   private void updateMapToFile (String file) {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(file));
         out.write("" + Map.numVerticalBlock);
         out.newLine();
         out.write("" + Map.numHorizontalBlock);
         out.newLine();
         for (int i = 0; i < charMap.numVerticalBlock; i ++) {
            for (int j = 0; j < charMap.numHorizontalBlock; j ++) {
               out.write("" + map[i][j]);
            }
            out.newLine();
         }
         out.close();
      } catch (IOException iox) {
         System.out.println("NO");
      }
   }
}