class GymMember {
    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Member ID must have at least 4 characters.");
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() { sessionsAttended++; }
    public int getSessionsAttended() { return sessionsAttended; }

    public String displayInfo() {
        return "Standard Member | Sessions: " + sessionsAttended;
    }
}

class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() { return trainerName; }

    @Override
    public String displayInfo() {
        return "Premium Member | Trainer: " + trainerName
            + " | Sessions: " + getSessionsAttended();
    }
}

class EliteMember extends PremiumMember {
    private String lockerNumber;

    public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    @Override
    public String displayInfo() {
        return "Elite Member | Trainer: " + getTrainerName()
            + " | Locker: " + lockerNumber + " | Sessions: " + getSessionsAttended();
    }
}

class GroupClassMember extends GymMember {
    private String className;

    public GroupClassMember(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    @Override
    public String displayInfo() {
        return "Group Class Member | Class: " + className
            + " | Sessions: " + getSessionsAttended();
    }
}

public class Main {
    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) return "Multilevel descendant (3 generations deep)";
        if (member instanceof GroupClassMember) return "Hierarchical sibling (independent branch)";
        if (member instanceof PremiumMember) return "Second generation";
        return "Base generation";
    }

    public static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        for (int i = 0; i < members.length; i++) total += members[i].getSessionsAttended();
        return total;
    }

    public static void main(String[] args) {
        GymMember[] members = {
            new GymMember("MEM1", 1000),
            new PremiumMember("MEM2", 2000, "Coach Riya"),
            new EliteMember("MEM3", 3000, "Coach Arjun", "L12"),
            new GroupClassMember("MEM4", 1500, "Zumba")
        };
        for (int i = 0; i < members.length; i++) System.out.println(members[i].displayInfo());
        System.out.println(classifyGeneration(members[2]));
        System.out.println("Total sessions: " + getTotalSessionsAttended(members));
    }
}
