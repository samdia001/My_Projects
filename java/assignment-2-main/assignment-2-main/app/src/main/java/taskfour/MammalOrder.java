package taskfour;

public class MammalOrder extends AnimalOrder {

    // Attributes.
    private String furColor;
    private boolean hasWinterFur;
  
    /**
     * Mammal constructor.
  
     * @param name .
     * @param latinName  .
     * @param weight .
     * @param sound .
     * @param furColor .
     * @param hasWinterFur .
     */
  
    public MammalOrder(String name, String latinName, Double weight,
        String sound, String furColor, boolean hasWinterFur) {
      super(name, latinName, weight, sound);
      this.furColor = furColor;
      this.hasWinterFur = hasWinterFur;
    }
  
    /**
     * method to get fur color.
  
     * @return .
     */
  
    public String getFurColor() {
      return this.furColor;
    }
  
    public void setFurColor(String furColor) {
      this.furColor = furColor;
    }
  
    /**
     * method to check if it has winter fur.
  
     * @return isHasWinterFur.
     */
  
    public boolean isHasWinterFur() {
      return this.hasWinterFur;
    }
  
    /**
     * method to get getHasWinterFur.
  
     * @return .
     */
  
    public boolean getHasWinterFur() {
      return this.hasWinterFur;
    }
  
    public void setHasWinterFur(boolean hasWinterFur) {
      this.hasWinterFur = hasWinterFur;
    }
  
    @Override
    public void makeSound() {
      System.out.println("A " + getName() + " says: " + getSound());
    }
  }
  
