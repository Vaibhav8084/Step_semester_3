import java.time.LocalDate;

abstract class LeaveEmployee {
    private String name;
    public LeaveEmployee(String name) { this.name = name; }
    public String getName() { return name; }
    public abstract int getMaximumLeaveDays();
}

class FullTimeEmployee extends LeaveEmployee {
    public FullTimeEmployee(String name) { super(name); }
    public int getMaximumLeaveDays() { return 30; }
}
class PartTimeEmployee extends LeaveEmployee {
    public PartTimeEmployee(String name) { super(name); }
    public int getMaximumLeaveDays() { return 15; }
}
class Contractor extends LeaveEmployee {
    public Contractor(String name) { super(name); }
    public int getMaximumLeaveDays() { return 5; }
}

class LeaveRequest {
    private LeaveEmployee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status = "Pending";

    public LeaveRequest(LeaveEmployee employee, LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) throw new IllegalArgumentException("End date cannot be before start date.");
        long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
        if (days > employee.getMaximumLeaveDays()) throw new IllegalArgumentException("Leave request exceeds the employee's limit.");
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public String getStatus() { return status; }
    public String getDateRange() { return startDate.getMonth().toString().substring(0, 1)
        + startDate.getMonth().toString().substring(1).toLowerCase() + " " + startDate.getDayOfMonth()
        + "-" + endDate.getDayOfMonth(); }

    public String review(String decision) {
        if (!status.equals("Pending")) {
            return "Cannot change leave request status from " + status + " to " + decision + ".";
        }
        if (!decision.equals("Approved") && !decision.equals("Rejected")) {
            return "Review decision must be Approved or Rejected.";
        }
        status = decision;
        return employee.getName() + "'s leave request (" + getDateRange() + ") "
            + decision.toLowerCase() + ". Status: " + status + ".";
    }
}

public class Question2_EmployeeLeaveWorkflow {
    public static void main(String[] args) {
        LeaveEmployee john = new FullTimeEmployee("John");
        LeaveEmployee jane = new PartTimeEmployee("Jane");
        LeaveRequest johnRequest = new LeaveRequest(john, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5));
        LeaveRequest janeRequest = new LeaveRequest(jane, LocalDate.of(2026, 2, 10), LocalDate.of(2026, 2, 11));

        System.out.println("Leave request submitted for John (Jan 1-5). Status: " + johnRequest.getStatus() + ".");
        System.out.println(johnRequest.review("Approved"));
        System.out.println("Leave request submitted for Jane (Feb 10-11). Status: " + janeRequest.getStatus() + ".");
        System.out.println(janeRequest.review("Rejected"));
        System.out.println(johnRequest.review("Pending"));
    }
}
