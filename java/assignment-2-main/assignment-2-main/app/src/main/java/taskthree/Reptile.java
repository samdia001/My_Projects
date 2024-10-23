package taskthree;

public class Reptile extends Animal {

    //ATTRIBUTES
    private String habitat;
    private Boolean isPoisonous;
  
    /**
     * Reptile CONSTRUCTOR.
  
     * @param name  name
     * @param latinName latinName
     * @param weight weight
     * @param sound sound
     * @param habitat habitat
     * @param isPoisonous isPoisonous
     */
  
    public Reptile(String name, String latinName, Double weight, String sound,
        String habitat, boolean isPoisonous) {
      super(name, latinName, weight, sound);
      this.habitat = habitat;
      this.isPoisonous = isPoisonous;
    }
  
    /**
     * Method to get habitat.
  
     * @return habitat
     */
  
    public String getHabitat() {
      return this.habitat;
    }
  
    public void setHabitat(String habitat) {
      this.habitat = habitat;
    }
  
    /**
     * Method to CHECK if it is poisonous.
  
     * @return isPoisonous
     */
  
    public Boolean isIsPoisonous() {
      return this.isPoisonous;
    }
  
    /**
     * Method to get IsPoisonous.
  
     * @return isPoisonous
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
