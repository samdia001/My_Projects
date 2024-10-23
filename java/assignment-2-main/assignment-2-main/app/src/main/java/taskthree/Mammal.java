package taskthree;

public class Mammal extends Animal {

    // ATTRIBUTES
    private String furColor;
    private boolean hasWinterFur;
  
    /**
     * Mammal CONSTRUCTOR.
  
     * @param name .
     * @param latinName .
     * @param weight .
     * @param sound  .
     * @param furColor .
     * @param hasWinterFur .
     */
  
    public Mammal(String name, String latinName, Double weight,
        String sound, String furColor, boolean hasWinterFur) {
      super(name, latinName, weight, sound);
      this.furColor = furColor;
      this.hasWinterFur = hasWinterFur;
    }
  
    /**
     * Method to get fur color.
  
     * @return .
     */
  
    public String getFurColor() {
      return this.furColor;
    }
  
    public void setFurColor(String furColor) {
      this.furColor = furColor;
    }
  
    /**
     * Method to CHECK if it has winter fur.
  
     * @return .
     */
  
    public boolean isHasWinterFur() {
      return this.hasWinterFur;
    }
  
    /**
     * Method to get Has Winter Fur.
  
     * @return .
     */
  
    public boolean getHasWinterFur() {
      return this.hasWinterFur;
    }
  
    public void setHasWinterFur(boolean hasWinterFur) {
      this.hasWinterFur = hasWinterFur;
    }
  
    public void makeSound() {
      System.out.println("A " + getName() + " says: " + getSound());
    }
  }