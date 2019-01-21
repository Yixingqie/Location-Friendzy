class Nature extends Block{
   // Constructor
   public Nature (int coordinateX, int coordinateY){
      super(null, coordinateX, coordinateY);
   }
   
   // Accessor
   public Block getAttraction(){
      return this;
   }
}