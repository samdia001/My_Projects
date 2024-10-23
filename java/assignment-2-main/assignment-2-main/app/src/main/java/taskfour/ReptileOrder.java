package taskfour;

public class ReptileOrder extends AnimalOrder {

    // ATTRIBUTES
    private String habitat;
    private Boolean isPoisonous;
  
    /**
     * Reptile constructor.
  
     * @param name .
     * @param latinName .
     * @param weight .
     * @param sound .
     * @param habitat  .
     * @param isPoisonous .
     */
  
    public ReptileOrder(String name, String latinName, Double weight, String sound,
        String habitat, boolean isPoisonous) {
      super(name, latinName, weight, sound);
      this.habitat = habitat;
      this.isPoisonous = isPoisonous;
    }
  
    /**
     * Method to get habitat.
  
     * @return .
     */
  
    public String getHabitat() {
      return this.habitat;
    }
  
    public void setHabitat(String habitat) {
      this.habitat = habitat;
    }
  
    /**
     * Method to check if it is poisonous.
  
     * @return .
     */
  
    public Boolean isIsPoisonous() {
      return this.isPoisonous;
    }
  
    /**
     * Method to get IsPoisonous.
  
     * @return .
     */
  
    public Boolean getIsPoisonous() {
      return this.isPoisonous;
    }
  
    public void setIsPoisonous(Boolean isPoisonous) {
      this.isPoisonous = isPoisonous;
    }
  
    public void makeSound() {
      System.out.println("A " + getName() + " hizzes: " + getSound());
    }
  }
