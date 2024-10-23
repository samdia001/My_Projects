package taskthree;

/**
 * abstract claas Animal.
 */
public abstract class Animal {
      // ATTRIBUTES
  private String name;
  private String latinName;
  private Double weight;
  protected String sound;

  /**
   * Animal CONSTRUCTOR.

   * @param name .
   * @param latinName . 
   * @param weight .
   * @param sound  .
   */

  public Animal(String name, String latinName, Double weight, String sound) {
    this.name = name;
    this.latinName = latinName;
    this.weight = weight;
    this.sound = sound;
  }

  abstract void makeSound();

  /**
   * Method to get name.

   * @return .
   */

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Method to get latin name.

   * @return .
   */

  public String getLatinName() {
    return this.latinName;
  }

  public void setLatinName(String latinName) {
    this.latinName = latinName;
  }

  /**
   * get weight.

   * @return .
   */

  public Double getWeight() {
    return this.weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  /**
   * Method to get sound.

   * @return .
   */

  public String getSound() {
    return this.sound;
  }

  public void setSound(String sound) {
    this.sound = sound;
  }
    
}
