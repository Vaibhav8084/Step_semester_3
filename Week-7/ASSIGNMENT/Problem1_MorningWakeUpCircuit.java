interface Ringable {
    String ring();
}

class AlarmClock implements Ringable {
    private String time;

    public AlarmClock(String time) {
        this.time = time;
    }

    @Override
    public String ring() {
        return "Alarm ringing for " + time;
    }
}

class Doorbell implements Ringable {
    private String location;

    public Doorbell(String location) {
        this.location = location;
    }

    @Override
    public String ring() {
        return "Doorbell ringing at " + location;
    }
}

public class Problem1_MorningWakeUpCircuit {
    public static void ringAll(Ringable[] devices) {
        for (int i = 0; i < devices.length; i++) {
            System.out.println(devices[i].ring());
        }
    }

    public static void main(String[] args) {
        Ringable[] devices = {
            new AlarmClock("7:00 AM"),
            new Doorbell("Front Door")
        };
        ringAll(devices);
    }
}
