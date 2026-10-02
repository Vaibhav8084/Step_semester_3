import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface NotificationChannel {
    void send(Student recipient, Notice notice);
}

class EmailChannel implements NotificationChannel {
    public void send(Student recipient, Notice notice) {
        System.out.println("[Email → " + recipient.getName() + "] " + notice.getTitle());
    }
}
class SmsChannel implements NotificationChannel {
    public void send(Student recipient, Notice notice) {
        System.out.println("[SMS → " + recipient.getName() + "] " + notice.getTitle());
    }
}
class AppChannel implements NotificationChannel {
    public void send(Student recipient, Notice notice) {
        System.out.println("[App → " + recipient.getName() + "] " + notice.getTitle());
    }
}

class Student {
    private String name;
    private String department;
    private List<NotificationChannel> preferredChannels;

    public Student(String name, String department, List<NotificationChannel> channels) {
        this.name = name;
        this.department = department;
        this.preferredChannels = new ArrayList<NotificationChannel>(channels);
    }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public List<NotificationChannel> getPreferredChannels() { return preferredChannels; }
}

class Notice {
    private String title;
    private List<String> targetDepartments;

    public Notice(String title, List<String> departments) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("A title is required.");
        }
        if (departments == null || departments.isEmpty()) {
            throw new IllegalArgumentException("At least one target department is required.");
        }
        this.title = title;
        this.targetDepartments = new ArrayList<String>(departments);
    }
    public String getTitle() { return title; }
    public List<String> getTargetDepartments() { return targetDepartments; }
}

class NoticeBoard {
    private List<Student> students = new ArrayList<Student>();

    public void addStudent(Student student) { students.add(student); }

    public void post(Notice notice) {
        System.out.println("Notice '" + notice.getTitle() + "' posted to "
            + String.join(", ", notice.getTargetDepartments()) + ".");
        for (Student student : students) {
            if (notice.getTargetDepartments().contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getPreferredChannels()) {
                    channel.send(student, notice);
                }
            }
        }
    }
}

public class Question5_CampusNoticeBroadcaster {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();
        board.addStudent(new Student("Asha", "CSE", Arrays.<NotificationChannel>asList(
            new EmailChannel(), new AppChannel())));
        board.addStudent(new Student("Ravi", "ECE", Arrays.<NotificationChannel>asList(
            new SmsChannel())));

        board.post(new Notice("Lab Closed Tomorrow", Arrays.asList("CSE")));
        board.post(new Notice("Fee Deadline Extended", Arrays.asList("CSE", "ECE")));
        try {
            board.post(new Notice("Sports Day", new ArrayList<String>()));
        } catch (IllegalArgumentException error) {
            System.out.println("Cannot post notice: " + error.getMessage());
        }
    }
}
