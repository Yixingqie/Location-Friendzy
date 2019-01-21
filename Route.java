class Route{
   private int xstart;
   private int ystart;
   private int endx;
   private int endy;
//	private char [] legend;
//	private int maxLegend;///
   private int maxCoordinates;///
   private String [] coordinates;
   final double WALKTIME = 3.5;
   final double CARTIME = 1.5;
   final double BUSTIME = 2;
   private double time;
   private char [][] map;
   private char[][] map2;
   private int length;
	
   // Constructor
   public Route(Block house, Event eve, Map mapChar){ 
      ystart = house.getX();
      xstart = house.getY();
      endy = eve.getBlock().getX();
      endx = eve.getBlock().getY();
      length = mapChar.getMap().length;
    //  System.out.println(length);
      map = new char[length][length]; 
      map2 = new char[length][length];//creates copy of the map 
    //  System.out.println(""+ystart+""+xstart);
     // System.out.println(""+ endy+""+endx);
      //does not modify the original map
      for(int i = 0; i < length; i++){
         for(int j = 0; j < length; j++){
            map[i][j] = mapChar.getMap()[i][j];
            map2[i][j] = mapChar.getMap()[i][j];
         }
      }
      map[endx-1][endy-1] = '*';
     // System.out.println(toString());
      findRoute(xstart-1,ystart-1);
      
      result();
      findCoordinates();
   }
   
   // Accessor
   public String[] getCoordinates(){
      return coordinates;
   }

   private void result (){
      for(int i = 0; i < length; i++){
         for(int j = 0; j < length; j++){
            if(map[i][j] == '*'||map[i][j] == '-')
               map2[i][j] = map[i][j];
         }
      }
      
      map2[xstart-1][ystart-1] = '+';
      
   
   }
   // finds a route
   private boolean findRoute(int x, int y){
      boolean suc = false;
      if (map[x][y] == '*') 
      {
         map[x][y] = '-';
         suc = true;
      }
      else {
         map[x][y] = 'T';
         
         if (!suc){
            if (y+1 < 9 && (streetCheck(map[x][y+1]) || map[x][y+1] == '*')) {
               suc = findRoute(x, y+1);
            }
         }
         if (!suc){
            if (x-1 > 0 &&(streetCheck(map[x-1][y]) || map[x-1][y] == '*')) {
               suc = findRoute(x-1, y);
            }
         }
         if (!suc){
            if (y-1 >0&&(streetCheck(map[x][y-1]) || map[x][y-1] == '*')) {
               suc = findRoute(x, y-1);
            }
          }
         if (!suc){
            if  (x+1 < 9&&(streetCheck(map[x+1][y]) || map[x+1][y] == '*' )) {
               suc = findRoute(x+1, y);
            }
         }
       
         if (suc) {
            map[x][y] = '*';
         }
      }
      return suc;
      	
   }
   
   //
   private void findCoordinates(){
      int count= 0;
      for(int i = 0;i< map.length; i++){
         for(int j = 0; j < map[i].length; j++){
            if(map[i][j]=='*'){
               count++;
            }
         }
      }
      maxCoordinates = count;
      coordinates = new String [maxCoordinates];
      count = 0;
      for(int i = 0;i< map.length; i++){
         for(int j = 0; j < map[i].length; j++){
            if(map[i][j]=='*'){
               coordinates[count] = i+","+j;
               count++;
            }
         }
      }
   
   }
   
   
   // Calculates the different times it will take for the route 
   // based on transportation methods
   public String calculateTime(){
      double busTime;
      double walkTime;
      double carTime;
      double delay = randomWeather();
      busTime = coordinates.length * BUSTIME * delay;
      walkTime = coordinates.length * WALKTIME;
      carTime = coordinates.length * CARTIME * delay;
      return "Bus Time: "+busTime+" mins \nWalk Time: "+walkTime+" mins \nCar Time: "+carTime+" mins";
   }


   
   // Checks if a block is a street
   private boolean streetCheck(char block){
      if(block == '1' ||block == '2' ||block == '3' ||block == '4'|| block == '5' ||block == '6' ||block == '7' ||block == '8' ){
         return true;
      }else{
         return false;
      }
   }
   
   // toString
   public String toString(){
      String print = "+ = start \n - = end \n";
      for(int i = 0; i < map2.length; i++){
         for(int j = 0; j < map2.length; j++){
            print +=map2[i][j];
         }
         print+="\n";
      }
      return print;
   }
   
   public double randomWeather(){
      double delayFactor = 0;
      int type =  (int)(Math.random()*3);
      switch(type){
         case 0:
            System.out.println("no delays");
            return 1;
         case 1:
            System.out.println("thunderstorm");
            return 1.5;
         case 2:
            System.out.println("car crash on route");
            return 2;
         case 3:
            System.out.println("traffic");
            return 2.2;
      }
      return 1;
   }

}