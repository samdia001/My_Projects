package taskfour;

public class BirdOrder extends AnimalOrder {

    // Attributes
    private Boolean isMigrant;
    private Boolean canFly;
    private String nestType;
  
    /**
     * Bird CONSTRUCTOR.
  
     * @param name  .
     * @param latinName .
     * @param weight  .
     * @param sound  .
     * @param isMigrant .
     * @param canFly .
     * @param nestType .
     */
  
    public BirdOrder(String name, String latinName, Double weight, String sound,
        boolean isMigrant, boolean canFly, String nestType) {
      super(name, latinName, weight, sound);
      this.isMigrant = isMigrant;
      this.canFly = canFly;
      this.nestType = nestType;
    }
  
    /**
     * method to check if it is migrant.
  
     * @return is migrant.
     */
  
    public Boolean isIsMigrant() {
      return this.isMigrant;
    }
  
    /**
     * method to get getIsMigrant.
  
     * @return .
     */
  
    public Boolean getIsMigrant() {
      return this.isMigrant;
    }
  
    public void setIsMigrant(Boolean isMigrant) {
      this.isMigrant = isMigrant;
    }
  
    /**
     * method to check if it can fly.
  
     * @return isCanFly.
     */
  
    public Boolean isCanFly() {
      return this.canFly;
    }
  
    public void setCanFly(Boolean canFly) {
      this.canFly = canFly;
    }
  
    /**
     * method to get nest type.
  
     * @return getNestType.
     */
  
    public String getNestType() {
      return this.nestType;
    }
  
    public void setNestType(String nestType) {
      this.nestType = nestType;
    }
  
    @Override
    public void makeSound() {
      System.out.println("A " + getName() + " tweets: " + getSound());
    }
  }
