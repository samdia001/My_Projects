package tasktwo;

public class Moon {

    private String name;
  private int size;

  public Moon(String nameOfMoon, int sizeInKm) {
    this.name = nameOfMoon;
    this.size = sizeInKm;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Method to get THE size.

   * @return size in km.
   */

  public int getSize() {
    return this.size;
  }

  public void setSize(int kmSize) {
    this.size = kmSize;
  }
    
}
