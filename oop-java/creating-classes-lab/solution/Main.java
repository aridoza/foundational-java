// import Animal class

public class Main {

    public static void main(String[] args) {

      Animal animal = new Animal(4,25,true,"Elephant");

      String name;
      int topSpeed;

      //get name and speed values using getters
      name = animal.getName();
      topSpeed = animal.getTopSpeed();

      output.setText("The "+name + " has a top speed of "+topSpeed+" mph");

      //Set new name, speed, and endangered properties values using setters
      animal.setName("Lion");
      animal.setTopSpeed(50);
      animal.setIsEndangered(false);

      //get new values using getters
      name = animal.getName();
      topSpeed = animal.getTopSpeed();

      //Set text of second TextView
      changedOutput.setText("The "+name+" has a top speed of "+topSpeed+" mph");
    }
}
