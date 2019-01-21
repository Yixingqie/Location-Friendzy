import java.util.*;
import java.io.*;
abstract class Block {
   protected String address;
   protected int coordinateX;
   protected int coordinateY;

   public Block (String address, int coordinateX, int coordinateY){
      this.address = address;
      this.coordinateX = coordinateX;
      this.coordinateY = coordinateY;
   }
   
   // Accessors
   
   public int getX(){
      return coordinateX;
   }
   public int getY(){
      return coordinateY;
   }
   public String getAddress(){
      return address;
   }
   
   // Do we still need this?
   public int generateBlockID(){
      return (int)(Math.random() * 30 + 1);
   }
   
   public abstract Block getAttraction();
   
//    public boolean equals (Block other){
//       return name.equals(other.getName());
//    }
   
}