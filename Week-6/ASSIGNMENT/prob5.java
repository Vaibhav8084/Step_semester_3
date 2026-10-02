class GymMember {
    private static int nextNumber = 2000;
    private final String membershipNumber;
    private int monthlyFee;
    private int feesPaid;

    public GymMember(int monthlyFee) {
        nextNumber++;
        membershipNumber = "GYM-" + nextNumber;
        this.monthlyFee = monthlyFee;
        feesPaid = 0;
    }

    public String getMembershipNumber() { return membershipNumber; }

    public void payFee(int amount) { feesPaid += amount; }

    public void payFee(int amount, String mode) {
        payFee(amount);
    }

    public int getFeesPaid() { return feesPaid; }

    public static int getMembersEnrolled() { return nextNumber - 2000; }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) return false;
        return code.charAt(0) == 'G'
            && Character.isDigit(code.charAt(1))
            && Character.isDigit(code.charAt(2))
            && Character.isUpperCase(code.charAt(3));
    }
}

class GroupClassMember extends GymMember {
    private String className;

    public GroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }
}

public class Main {
    public static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (int i = 0; i < members.length; i++) {
            if (members[i] == null) {
                nullSkipped++;
            } else {
                processed++;
                if (members[i] instanceof GroupClassMember) group++;
                else individual++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
            + group + " group | " + individual + " individual";
    }

    public static void main(String[] args) {
        GymMember member = new GymMember(1000);
        member.payFee(500);
        member.payFee(500, "UPI");
        System.out.println(member.getMembershipNumber());
        System.out.println("Fees paid: " + member.getFeesPaid());
        System.out.println(GymMember.isValidReferralCode("G45B"));

        GymMember[] week = {
            new GroupClassMember(1500, "Zumba"),
            null,
            new GymMember(1000)
        };
        System.out.println(processWeeklyCheckIn(week));
    }
}
