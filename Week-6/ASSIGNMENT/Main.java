class GymMember {
    private String memberId;
    private int monthlyFee;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Member ID must have at least 4 characters.");
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }
}

class PremiumMember extends GymMember {
    private String trainerName;
    private int sessionsAttended;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
        this.sessionsAttended = 0;
    }

    public void attendSession() { sessionsAttended++; }
    public int getSessionsAttended() { return sessionsAttended; }
}

public class Main {
    public static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;
        for (int i = 0; i < memberIds.length; i++) {
            try {
                new GymMember(memberIds[i], monthlyFee);
                signedUp++;
            } catch (IllegalArgumentException error) {
                rejected++;
            }
        }
        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        String[] ids = {"MEM1", "GM1", "MEM2", " ", "MEM3"};
        System.out.println(signUpBatch(ids, 1000));
    }
}
