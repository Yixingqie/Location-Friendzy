class Street extends Block{
   private String streetName;
   private int xend;
   private int yend;
   private int num;
   
   // Constructor
    public Street (int id,int coordinateX, int coordinateY, int xend, int yend, String streetName, int num){///////////////////////////////////////
      super(null, coordinateX, coordinateY);
      this.streetName = streetName;
      this.xend = xend;
      this.yend = yend;
      this.num  = num;
   }
   
   // Accessors
   public Block getAttraction(){
      return this;
   }
   
   public int getNum() {
      return num;
   }

   
   public int getXEnd() {
      return xend;
   }
   
   public int getYEnd() {
      return yend;
   }
   
   public String getName(){
      return streetName;
   }
}