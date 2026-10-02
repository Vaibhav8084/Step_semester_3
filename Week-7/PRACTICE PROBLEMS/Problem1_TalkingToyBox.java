abstract class Toy {
    private static int nextNumber = 1000;
    private final String toyId;

    public Toy() {
        nextNumber++;
        toyId = "TOY-" + nextNumber;
    }

    public String getToyId() {
        return toyId;
    }

    public abstract String makeSound();
}

class ToyCar extends Toy {
    private String name;

    public ToyCar(String name) {
        super();
        this.name = name;
    }

    @Override
    public String makeSound() {
        return name + ": Vroom vroom!";
    }
}

class ToyRobot extends Toy {
    private String name;

    public ToyRobot(String name) {
        super();
        this.name = name;
    }

    @Override
    public String makeSound() {
        return name + ": Beep boop!";
    }
}

public class Problem1_TalkingToyBox {
    public static void main(String[] args) {
        ToyCar car = new ToyCar("Speedster");
        ToyRobot robot = new ToyRobot("Bolt");
        System.out.println(car.makeSound());
        System.out.println(car.getToyId());
        System.out.println(robot.makeSound());
        System.out.println(robot.getToyId());
        // Toy cannot be instantiated because it is abstract.
    }
}
