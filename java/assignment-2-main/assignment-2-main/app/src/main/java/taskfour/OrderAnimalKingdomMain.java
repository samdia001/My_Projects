package taskfour;


import java.util.ArrayList;
import java.util.Collections;
  
public class OrderAnimalKingdomMain {

    public static void main(String[] args) {

        ArrayList<AnimalOrder> animals = new ArrayList<AnimalOrder>();
        animals.add(new ReptileOrder("Nile crocodile", "Crocodilus niloticus",
            100.5, " ", "fresh water", true));
        animals.add(new MammalOrder("Tiger", "Panthera tigris",
            400.5, " ", "yellow and black", true));
        animals.add(new BirdOrder("Peafowl", "Pavo cristatus",
            2.5, "Kaka-kaka", true, true, "in/on a whole in the ground"));
        animals.add(new BirdOrder("Kiwi", "Apteryx mantelli",
            2.2, " ", true, true, "in/on a whole in the ground"));
        animals.add(new MammalOrder("Swedish red and white cattle", "Bos Taurus",
            25.5, "Muuuuu!", "red and white", false));
        animals.add(new ReptileOrder("Green sea turtle", "Chelonia mydas",
            115.5, " ", "the sea", false));
    
        Collections.sort(animals, Collections.reverseOrder());
        for (AnimalOrder animal : animals) {
    
          String[] arrayOfClasses = animal.getClass().getName().split("\\.");
          String nameOfClass = arrayOfClasses[1];
    
          if (nameOfClass.equals("BirdOrder")) {
            BirdOrder bird = (BirdOrder) animal;
            System.out.println(bird.getLatinName() + " puts it's egg " + bird.getNestType());
          
          } else if (nameOfClass.equals("MammalOrder")) {
            MammalOrder mammal = (MammalOrder) animal;
            System.out.println(mammal.getLatinName() + " has a fur that is "
                + mammal.getFurColor());
                
          } else if (nameOfClass.equals("ReptileOrder")) {
            ReptileOrder reptile = (ReptileOrder) animal;
            System.out.println(reptile.getLatinName() + " lives in " + reptile.getHabitat());
          }
        }
      }
    
}
