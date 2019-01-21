import java.io.*;
class Map{
//	private Blocks [][]map;
   public static int numVerticalBlock;
   public static int numHorizontalBlock;
   private char [][] mapChars;
   private String file;
   
   // Constructor
   public Map(String file){
      this.file = file;
      try{
         BufferedReader in = new BufferedReader(new FileReader(file));
         numVerticalBlock = Integer.parseInt(in.readLine());
         numHorizontalBlock = Integer.parseInt(in.readLine());
         mapChars = new char [numVerticalBlock][numHorizontalBlock];
         for(int h = 0; h < numHorizontalBlock; h++){
            String line = in.readLine();
            for(int j = 0;  j < line.length(); j++){
               mapChars[h][j] = line.charAt(j);
            }
         }	
         in.close();
      }catch(IOException iox){
         System.out.println("Error");
      }
   }
	
   // Accessor
   public char[][] getMap(){
      return mapChars;
   }
   
   // ...
  //  public void updateMap(Route[] routes){
//       for(int i  = 0; i < routes.length; i++){
//          String [] coordinates = routes [i].getCoordinates();
//          for(int j = 0; j < coordinates.length; j++){
//             String [] single = coordinates[i].split(",");
//             int xval = Integer.parseInt(single[0]);
//             int yval = Integer.parseInt(single[1]);
//             mapChars[xval][yval] = '*';
//          }
//       }
//    }
	
   // toString
   public String toString(){
      String print = "";
      for(int i = 0; i < numVerticalBlock; i++){
         for(int j = 0; j < numHorizontalBlock; j++){
            print +=mapChars[i][j];
         }
         print+="\n";
      }
      return print;
   }
}