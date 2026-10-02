import java.util.Locale;

interface WashType {
    int getDurationMinutes();
    double getCharge();
    String getName();
}

class QuickWash implements WashType {
    public int getDurationMinutes() { return 30; }
    public double getCharge() { return 20.0; }
    public String getName() { return "Quick"; }
}

class NormalWash implements WashType {
    public int getDurationMinutes() { return 45; }
    public double getCharge() { return 30.0; }
    public String getName() { return "Normal"; }
}

class HeavyWash implements WashType {
    public int getDurationMinutes() { return 60; }
    public double getCharge() { return 45.0; }
    public String getName() { return "Heavy"; }
}

class LaundryStudent {
    private String name;
    public LaundryStudent(String name) { this.name = name; }
    public String getName() { return name; }
}

class WashCycle {
    private LaundryStudent student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(LaundryStudent student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public LaundryStudent getStudent() { return student; }
    public WashingMachine getMachine() { return machine; }
    public WashType getWashType() { return washType; }
}

class WashingMachine {
    private String id;
    private WashCycle currentCycle;

    public WashingMachine(String id) { this.id = id; }
    public String getId() { return id; }
    public boolean isBusy() { return currentCycle != null; }

    public String startWash(LaundryStudent student, WashType type) {
        if (isBusy()) return "Machine " + id + " is currently busy.";
        currentCycle = new WashCycle(student, this, type);
        return String.format(Locale.US,
            "%s wash started on %s for %s (%d min). Charge: ₹%.2f.",
            type.getName(), id, student.getName(), type.getDurationMinutes(), type.getCharge());
    }

    public String completeCycle() {
        if (!isBusy()) return id + " has no active cycle.";
        currentCycle = null;
        return id + " cycle completed. " + id + " is now free.";
    }
}

public class Question1_HostelLaundryQueue {
    public static void main(String[] args) {
        LaundryStudent asha = new LaundryStudent("Asha");
        LaundryStudent ravi = new LaundryStudent("Ravi");
        LaundryStudent neha = new LaundryStudent("Neha");
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        System.out.println(m1.startWash(asha, new QuickWash()));
        System.out.println(m1.startWash(ravi, new HeavyWash()));
        System.out.println(m2.startWash(ravi, new HeavyWash()));
        System.out.println(m1.completeCycle());
        System.out.println(m1.startWash(neha, new NormalWash()));
    }
}
