package taskthree;

public class AnimlalMain {

    /**
     * Animal Main WHICH PRINTS the code.
  
     * @param args main.
     */
  
    public static void main(String[] args) {
  
      Mammal mammal = new Mammal("Swedish red and white cattle", "Bos Taurus",
          25.5, "Muuuuu!", "Brown", false);
  
      mammal.makeSound();
      Bird bird = new Bird("Peafowl", "Pavo cristatus", 2.5, "Kaka-kaka",
          true, true, "In/on a hole in the ground");
  
      bird.makeSound();
      Reptile reptile = new Reptile("Comodo dragon", "Varanus komodoensis",
          10.5, "kshhhhhh kshhhh", "Island", false);
  
      reptile.makeSound();
      System.out.println();
    }
  }
