class GymMember {
    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;
    private int[] lateFeeHistory = new int[10];
    private int lateFeeCount;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Member ID must have at least 4 characters.");
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    public void attendSession() { sessionsAttended++; }
    public int getSessionsAttended() { return sessionsAttended; }

    protected void chargeLateFee(int amount) {
        if (lateFeeCount < lateFeeHistory.length) lateFeeHistory[lateFeeCount++] = amount;
    }

    public int[] getLateFeeHistory() {
        int[] copy = new int[lateFeeCount];
        for (int i = 0; i < lateFeeCount; i++) copy[i] = lateFeeHistory[i];
        return copy;
    }

    public int getTotalLateFees() {
        int total = 0;
        for (int i = 0; i < lateFeeCount; i++) total += lateFeeHistory[i];
        return total;
    }
}

class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }
}

public class Main {
    public static void main(String[] args) {
        PremiumMember member = new PremiumMember("MEM5", 2000, "Coach Riya");
        member.chargeLateFee(200);
        System.out.println("Total late fees: " + member.getTotalLateFees());
        int[] history = member.getLateFeeHistory();
        for (int i = 0; i < history.length; i++) System.out.println(history[i]);
    }
}
