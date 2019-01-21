class House extends Block{
   private String phoneNumber;
   private String name;
   
   // Constructor
   public House (String address, String name, String phoneNumber, int coordinateX, int coordinateY){
      super(address, coordinateX, coordinateY);
      this.phoneNumber = phoneNumber;
      this.name = name;
   }
   
   // Accessor
   public Block getAttraction(){
      return this;
   }
   
   public String getName(){
      return name;
   }
   
   public String getPhoneNum(){
      return phoneNumber;
   }
}