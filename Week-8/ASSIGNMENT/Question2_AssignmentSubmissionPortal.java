import java.time.LocalDate;

class PortalStudent {
    private String name;
    public PortalStudent(String name) { this.name = name; }
    public String getName() { return name; }
}

abstract class PortalAssignment {
    private String title;
    private int maxMarks;
    private LocalDate dueDate;

    public PortalAssignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }
    public String getTitle() { return title; }
    public int getMaxMarks() { return maxMarks; }
    public LocalDate getDueDate() { return dueDate; }
    public abstract double applyPenalty(int marks, long lateDays);
}

class CodingAssignment extends PortalAssignment {
    public CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }
    public double applyPenalty(int marks, long lateDays) {
        return marks * Math.max(0.0, 1.0 - 0.10 * lateDays);
    }
}

class WrittenAssignment extends PortalAssignment {
    public WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }
    public double applyPenalty(int marks, long lateDays) {
        return marks * Math.max(0.0, 1.0 - 0.20 * lateDays);
    }
}

class Submission {
    private PortalStudent student;
    private PortalAssignment assignment;
    private LocalDate submissionDate;
    private String status = "Submitted";
    private int finalMarks;
    private long lateDays;

    public Submission(PortalStudent student, PortalAssignment assignment, LocalDate date) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = date;
        this.lateDays = Math.max(0, java.time.temporal.ChronoUnit.DAYS.between(assignment.getDueDate(), date));
    }

    public String getStatus() { return status; }

    public String grade(int awardedMarks) {
        if (!status.equals("Submitted")) return "Cannot grade: submission is already Graded.";
        if (awardedMarks < 0 || awardedMarks > assignment.getMaxMarks()) {
            return "Invalid marks: must be between 0 and " + assignment.getMaxMarks() + ".";
        }
        finalMarks = (int) Math.round(assignment.applyPenalty(awardedMarks, lateDays));
        status = "Graded";
        String result = student.getName() + " graded: " + finalMarks + "/" + assignment.getMaxMarks();
        if (lateDays > 0) result += " after " + (lateDays * 10) + "% late penalty";
        return result + ". Status: Graded.";
    }

    public String resubmit(LocalDate newDate) {
        if (status.equals("Graded")) {
            return "Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.";
        }
        submissionDate = newDate;
        lateDays = Math.max(0, java.time.temporal.ChronoUnit.DAYS.between(assignment.getDueDate(), newDate));
        return student.getName() + " resubmitted '" + assignment.getTitle() + "'.";
    }
}

public class Question2_AssignmentSubmissionPortal {
    public static void main(String[] args) {
        PortalAssignment coding = new CodingAssignment("Linked List Lab", 50, LocalDate.of(2026, 3, 10));
        PortalAssignment written = new WrittenAssignment("Design Essay", 50, LocalDate.of(2026, 3, 12));
        PortalStudent asha = new PortalStudent("Asha");
        PortalStudent ravi = new PortalStudent("Ravi");

        Submission ashaSubmission = new Submission(asha, coding, LocalDate.of(2026, 3, 10));
        Submission raviSubmission = new Submission(ravi, written, LocalDate.of(2026, 3, 14));
        System.out.println("Asha's submission for 'Linked List Lab' received (on time). Status: Submitted.");
        System.out.println("Ravi's submission for 'Design Essay' received (2 days late). Status: Submitted.");
        System.out.println(ashaSubmission.grade(45));
        System.out.println(raviSubmission.grade(40));
        System.out.println(ashaSubmission.resubmit(LocalDate.of(2026, 3, 11)));
    }
}
