package taskfour;

public abstract class AnimalOrder implements Comparable<AnimalOrder> {

    // Attributes
    private String name;
    private String latinName;
    private Double weight;
    protected String sound;
  
    /**
     * Animal constructor.
  
     * @param name .
     * @param latinName .
     * @param weight .
     * @param sound  .
     */
  
    public AnimalOrder(String name, String latinName, Double weight, String sound) {
      this.name = name;
      this.latinName = latinName;
      this.weight = weight;
      this.sound = sound;
    }
  
    /**
     * Method to COMPARE the animals.
     */
  
    @Override
    public int compareTo(AnimalOrder animal) {
      return animal.getLatinName().compareTo(this.getLatinName());
    }
  
    public abstract void makeSound();
  
    /**
     * method to get name.
  
     * @return .
     */
  
    public String getName() {
      return this.name;
    }
  
    public void setName(String name) {
      this.name = name;
    }
  
    /**
     * method to get latin name.
  
     * @return .
     */
  
    public String getLatinName() {
      return this.latinName;
    }
  
    public void setLatinName(String latinName) {
      this.latinName = latinName;
    }
  
    /**
     * method to get weight.
  
     * @return .
     */
  
    public Double getWeight() {
      return this.weight;
    }
  
    public void setWeight(Double weight) {
      this.weight = weight;
    }
  
    /**
     * method to get sound.
  
     * @return .
     */
  
    public String getSound() {
      return this.sound;
    }
  
    public void setSound(String sound) {
      this.sound = sound;
    }
  }