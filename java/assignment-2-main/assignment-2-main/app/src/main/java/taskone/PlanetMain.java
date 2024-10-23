package taskone;

public class PlanetMain {
    public static void main(String[] args) {

        Planet earth = new Planet(); // 1st for Earth  
        earth.setName("Earth:");
        earth.setPosition(3);
        earth.setNoMoons(1);
        earth.setAphelion(152097701);
        earth.setPerhelion(147098074);
    
        System.out.println(earth.getName());
        System.out.println("  Position: " + earth.getPosition());
        System.out.println("  Moons: " + earth.getNoOfMoons());
        System.out.println("  Aphelion: " + earth.getAphelion() + " km");
        System.out.println("  Perihelion: " + earth.getPerihelion() + " km");
    
        Planet mars = new Planet(); // 2nd for Mars
        mars.setName("Mars:");
        mars.setPosition(4);
        mars.setNoMoons(2);
        mars.setAphelion(249209300);
        mars.setPerhelion(206669000);
    
        System.out.println(mars.getName());
        System.out.println("  Position: " + mars.getPosition());
        System.out.println("  Moons: " + mars.getNoOfMoons());
        System.out.println("  Aphelion: " + mars.getAphelion() + " km");
        System.out.println("  Perihelion: " + mars.getPerihelion() + " km");
      }
}
    

