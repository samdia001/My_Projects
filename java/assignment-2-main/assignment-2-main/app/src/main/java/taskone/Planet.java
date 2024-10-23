package taskone;

public class Planet {
    private String name;
  private int position;
  private int noOfMoons;
  private int aphelion;
  private int perihelion;

  public Planet() {
  }


  public Planet(String newName, int pos, int noMoon, int aphe, int perih) {
    this.name = newName;
    this.position = pos;
    this.noOfMoons = noMoon;
    this.aphelion = aphe;
    this.perihelion = perih;
  }

  public String getName() {
    if (name.equals("")) {
      return "unknown";
    } else {
      return name;
    }
  }

 

  public void setName(String newName) {
    if (newName.equals("")) {
      name = "unknown";
    } else {
      name = newName;
    }
  }

  public int getPosition() {
    return position;
  }

 

  public void setPosition(int newPosition) {
    if (newPosition <= 0) {
      position = 0;
    } else {
      position = newPosition;
    }
  }

  public int getNoOfMoons() {
    return noOfMoons;
  }

  /**
   * numbers of the moon should not be negative.

   * @param newCount count the numbers of moon.
   */

  public void setNoMoons(int newCount) {
    if (newCount <= 0) {
      noOfMoons = 0;
    } else {
      noOfMoons = newCount;
    }
  }

  public int getAphelion() {
    return aphelion;
  }

  /**
   * Aphelions.

   * @param newAphelion .
   */

  public void setAphelion(int newAphelion) {
    if (newAphelion <= 0) {
      aphelion = 0;
    } else {
      aphelion = newAphelion;
    }
  }

  public int getPerihelion() {
    return perihelion;
  }

  /**
   * perihelion.

   * @param newPerhelion .
   */

  public void setPerhelion(int newPerhelion) {
    if (newPerhelion <= 0) {
      perihelion = 0;
    } else {
      perihelion = newPerhelion;
    }
  }
}
