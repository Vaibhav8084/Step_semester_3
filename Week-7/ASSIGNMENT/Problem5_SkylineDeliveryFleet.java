abstract class Drone {
    public Drone() {
    }

    public abstract String fly();
}

interface Trackable {
    String getLocation();
}

class DeliveryDrone extends Drone implements Trackable {
    private String id;

    public DeliveryDrone(String id) {
        this.id = id;
    }

    @Override
    public String fly() {
        return "Delivery drone " + id + " is flying";
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}

class ScoutDrone extends Drone {
    private String id;

    public ScoutDrone(String id) {
        this.id = id;
    }

    @Override
    public String fly() {
        return "Scout drone " + id + " is flying";
    }
}

class GroundRobot implements Trackable {
    private String id;

    public GroundRobot(String id) {
        this.id = id;
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}

public class Problem5_SkylineDeliveryFleet {
    public static String getLocationIfTrackable(Object object) {
        if (object instanceof Trackable) {
            Trackable trackable = (Trackable) object;
            return trackable.getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        DeliveryDrone deliveryDrone = new DeliveryDrone("DR-1");
        ScoutDrone scoutDrone = new ScoutDrone("SC-1");
        GroundRobot robot = new GroundRobot("GR-1");
        System.out.println(getLocationIfTrackable(deliveryDrone));
        System.out.println(getLocationIfTrackable(scoutDrone));
        System.out.println(getLocationIfTrackable(robot));
    }
}
