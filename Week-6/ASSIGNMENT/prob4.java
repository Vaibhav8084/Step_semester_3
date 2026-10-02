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
    }

    public void attendSession() { sessionsAttended++; }
    public int getSessionsAttended() { return sessionsAttended; }

    public String displayInfo() {
        return "Standard | Sessions: " + sessionsAttended;
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
        return "Premium | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
    }
}

class EliteMember extends PremiumMember {
    public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
    }
}

class GroupClassMember extends GymMember {
    public GroupClassMember(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
    }
}

public class Main {
    public static String batchPrint(GymMember[] members) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < members.length; i++) {
            GymMember member = members[i];
            result.append(member.displayInfo());
            if (member instanceof PremiumMember) {
                PremiumMember premium = (PremiumMember) member;
                result.append(" [Trainer via downcast: ");
                result.append(premium.getTrainerName());
                result.append("]");
            }
            result.append(" | ");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        GymMember[] members = {
            new GymMember("MEM6", 1000),
            new PremiumMember("MEM7", 2000, "Coach Riya")
        };
        System.out.println(batchPrint(members));
    }
}
