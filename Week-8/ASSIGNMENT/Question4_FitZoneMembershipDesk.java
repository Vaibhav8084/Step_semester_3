import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

interface MembershipPlan {
    String getName();
    int getMonths();
    double calculateFee();
}

class MonthlyPlan implements MembershipPlan {
    public String getName() { return "Monthly"; }
    public int getMonths() { return 1; }
    public double calculateFee() { return 1000.0; }
}
class QuarterlyPlan implements MembershipPlan {
    public String getName() { return "Quarterly"; }
    public int getMonths() { return 3; }
    public double calculateFee() { return 1000.0 * 3 * 0.90; }
}
class AnnualPlan implements MembershipPlan {
    public String getName() { return "Annual"; }
    public int getMonths() { return 12; }
    public double calculateFee() { return 1000.0 * 12 * 0.75; }
}

class GymMembership {
    private MembershipPlan plan;
    private String status = "Active";

    public GymMembership(MembershipPlan plan) { this.plan = plan; }
    public String getStatus() { return status; }
    public MembershipPlan getPlan() { return plan; }
    public boolean checkIn() { return status.equals("Active"); }
    public boolean freeze() {
        if (!status.equals("Active")) return false;
        status = "Frozen";
        return true;
    }
    public boolean unfreeze() {
        if (!status.equals("Frozen")) return false;
        status = "Active";
        return true;
    }
    public boolean expire() {
        if (status.equals("Expired")) return false;
        status = "Expired";
        return true;
    }
}

class GymMember {
    private String name;
    private GymMembership membership;

    public GymMember(String name) { this.name = name; }
    public String getName() { return name; }
    public GymMembership buyMembership(MembershipPlan plan) {
        membership = new GymMembership(plan);
        return membership;
    }
    public GymMembership getMembership() { return membership; }
}

public class Question4_FitZoneMembershipDesk {
    private static List<String> members = new ArrayList<String>();

    private static void buy(GymMember member, MembershipPlan plan) {
        member.buyMembership(plan);
        members.add(member.getName());
        System.out.printf(Locale.US, "%s membership created for %s. Fee: ₹%.2f. Status: Active.%n",
            plan.getName(), member.getName(), plan.calculateFee());
    }

    public static void main(String[] args) {
        GymMember asha = new GymMember("Asha");
        GymMember ravi = new GymMember("Ravi");
        buy(asha, new QuarterlyPlan());
        buy(ravi, new MonthlyPlan());

        if (asha.getMembership().checkIn()) System.out.println("Asha checked in successfully.");
        if (asha.getMembership().freeze())
            System.out.println("Asha's membership frozen. Status: " + asha.getMembership().getStatus() + ".");
        if (!asha.getMembership().checkIn())
            System.out.println("Check-in denied: Asha's membership is " + asha.getMembership().getStatus() + ".");
        if (ravi.getMembership().expire())
            System.out.println("Ravi's membership expired. Status: " + ravi.getMembership().getStatus() + ".");
        if (!ravi.getMembership().freeze()) System.out.println("Cannot freeze an Expired membership.");
    }
}
