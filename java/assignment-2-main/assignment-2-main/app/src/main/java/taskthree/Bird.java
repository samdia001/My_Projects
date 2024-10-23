package taskthree;

public class Bird extends Animal {

    // ATTRIBUTES
    private Boolean isMigrant;
    private Boolean canFly;
    private String nestType;
  
    /**
     * Bird CONSTRUCTOR.
  
     * @param name .
     * @param latinName .
     * @param weight .
     * @param sound  .
     * @param isMigrant .
     * @param canFly .
     * @param nestType .
     */
  
    public Bird(String name, String latinName, Double weight, String sound,
        boolean isMigrant, boolean canFly, String nestType) {
      super(name, latinName, weight, sound);
      this.isMigrant = isMigrant;
      this.canFly = canFly;
      this.nestType = nestType;
    }
  
    /**
     * migrant birds.
  
     * @return .
     */
  
    public Boolean isIsMigrant() {
      return this.isMigrant;
    }
  
    /**
     * Method to get Ismigrant.
  
     * @return .
     */
  
    public Boolean getIsMigrant() {
      return this.isMigrant;
    }
  
    public void setIsMigrant(Boolean isMigrant) {
      this.isMigrant = isMigrant;
    }
  
    /**
     * fly.
  
     * @return .
     */
  
    public Boolean isCanFly() {
      return this.canFly;
    }
  
    public void setCanFly(Boolean canFly) {
      this.canFly = canFly;
    }
  
    /**
     * Returns nest type.
  
     * @return getNestType.
     */
  
    public String getNestType() {
      return this.nestType;
    }
  
    public void setNestType(String nestType) {
      this.nestType = nestType;
    }
  
    public void makeSound() {
      System.out.println("A " + getName() + " tweets: " + getSound());
  
    }
  }
