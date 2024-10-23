package tasktwo;


import java.util.*;

public class PlanetWithMoon {
    private String name;
  private int position;
  private int noOfMoons;
  private int aphelion;
  private int perihelion;
  private ArrayList<Moon> moons;

  /**
   * Planet CONSTRUCTOR.

   * @param name .
   * @param position .
   * @param noOfMoons .
   * @param aphelion .
   * @param perihelion .
   * @throws Exception .
   */

  public PlanetWithMoon(String name, int position, int noOfMoons, int aphelion,
      int perihelion) throws Exception {
    this.name = lengthOfChar(name);
    this.position = ifItIsPositive(position);
    this.noOfMoons = ifItIsPositive(noOfMoons);
    this.aphelion = ifItIsPositive(aphelion);
    this.perihelion = ifItIsPositive(perihelion);
    moons = new ArrayList<>();
  }

  public String getName() {
    return this.name;
  }

  public void setName(String newName) {
    this.name = newName;
  }

  /**
   * Method to get the position.

   * @return .
   */

  public int getPosition() {
    return this.position;
  }

  public void setPosition(int newPos) {
    this.position = newPos;
  }

  /**
   * Method to get number of moons.

   * @return .
   */

  public int getNoOfMoons() {
    return this.noOfMoons;
  }

  public void addMoon(Moon newMoon) {
    this.moons.add(newMoon);
  }

  /**
   * Method Array.

   * @return moonArray
   */

  public Moon[] getMoons() {
    Moon[] moonArray = new Moon[this.moons.size()];
    for (int i = 0; i < this.moons.size(); i++) {
      moonArray[i] = moons.get(i);
    }
    return moonArray;
  }

  /**
   * Returns aphelion.

   * @return aphelion.
   */

  public int getAphelion() {
    return this.aphelion;
  }

  public void setAphelion(int newAphelion) {
    this.aphelion = newAphelion;
  }

  /**
   * get Perihelion.

   * @return perihelion
   */

  public int getPerihelion() {
    return this.perihelion;
  }

  public void setPerihelion(int newPerihelion) {
    this.perihelion = newPerihelion;
  }

  /**
   * Returns lenChar.

   * @return lenChar.
   * @throws Exception throws exception.
   */

  private String lengthOfChar(String lenChar) throws Exception {
    if (lenChar.length() < 2) {
      throw new Exception("2 characters at least!");
    }
    return lenChar;
  }

  /**
   * Method to check if It Is Positive.

   * @return .
   * @throws Exception .
   */

  private int ifItIsPositive(int digits) throws Exception {
    if (digits < 0) {
      throw new Exception("only positive it takes");
    } else {
      return digits;
    }
  }
    
}
