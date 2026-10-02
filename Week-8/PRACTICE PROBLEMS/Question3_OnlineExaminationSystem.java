import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class ExamStudent {
    private String name;
    public ExamStudent(String name) { this.name = name; }
    public String getName() { return name; }
}

abstract class ExamQuestion {
    private String id;
    private int points;
    public ExamQuestion(String id, int points) { this.id = id; this.points = points; }
    public String getId() { return id; }
    public int getPoints() { return points; }
    public abstract boolean isCorrect(String answer);
}

class MultipleChoiceQuestion extends ExamQuestion {
    private String correctOption;
    public MultipleChoiceQuestion(String id, int points, String correctOption) {
        super(id, points); this.correctOption = correctOption;
    }
    public boolean isCorrect(String answer) { return correctOption.equalsIgnoreCase(answer); }
}
class TrueFalseQuestion extends ExamQuestion {
    private boolean correctAnswer;
    public TrueFalseQuestion(String id, int points, boolean correctAnswer) {
        super(id, points); this.correctAnswer = correctAnswer;
    }
    public boolean isCorrect(String answer) { return Boolean.toString(correctAnswer).equalsIgnoreCase(answer); }
}
class ShortAnswerQuestion extends ExamQuestion {
    private String correctAnswer;
    public ShortAnswerQuestion(String id, int points, String correctAnswer) {
        super(id, points); this.correctAnswer = correctAnswer;
    }
    public boolean isCorrect(String answer) { return correctAnswer.equalsIgnoreCase(answer.trim()); }
}

class Examination {
    private String name;
    private List<ExamQuestion> questions;
    public Examination(String name, List<ExamQuestion> questions) {
        this.name = name; this.questions = new ArrayList<ExamQuestion>(questions);
    }
    public String getName() { return name; }
    public List<ExamQuestion> getQuestions() { return questions; }
    public int getMaximumScore() {
        int total = 0;
        for (ExamQuestion question : questions) total += question.getPoints();
        return total;
    }
}

class ExamAttempt {
    private ExamStudent student;
    private Examination examination;
    private Map<String, String> answers = new LinkedHashMap<String, String>();
    private boolean submitted;

    public ExamAttempt(ExamStudent student, Examination examination) {
        this.student = student; this.examination = examination;
    }
    public String answer(ExamQuestion question, String answer) {
        if (submitted) return "Cannot change answers for a submitted examination.";
        boolean found = false;
        for (ExamQuestion item : examination.getQuestions()) if (item == question) found = true;
        if (!found) return "Question is not part of this examination.";
        answers.put(question.getId(), answer);
        return "Answer recorded for " + question.getId() + ".";
    }
    public String submit() {
        if (submitted) return "This examination has already been submitted.";
        submitted = true;
        StringBuilder result = new StringBuilder(examination.getName() + " submitted by " + student.getName() + ". Result: ");
        int score = 0;
        for (int i = 0; i < examination.getQuestions().size(); i++) {
            ExamQuestion question = examination.getQuestions().get(i);
            String answer = answers.get(question.getId());
            boolean correct = answer != null && question.isCorrect(answer);
            int earned = correct ? question.getPoints() : 0;
            score += earned;
            if (i > 0) result.append(", ");
            result.append(question.getId()).append(": ").append(correct ? "Correct" : "Incorrect")
                .append(" (").append(earned).append(" points)");
        }
        result.append(". Total score: ").append(score).append("/").append(examination.getMaximumScore()).append(".");
        return result.toString();
    }
}

public class Question3_OnlineExaminationSystem {
    public static void main(String[] args) {
        ExamStudent student = new ExamStudent("Student 1");
        ExamQuestion q1 = new MultipleChoiceQuestion("Question 1", 5, "C");
        ExamQuestion q2 = new TrueFalseQuestion("Question 2", 5, false);
        Examination exam = new Examination("Exam A", java.util.Arrays.asList(q1, q2));
        ExamAttempt attempt = new ExamAttempt(student, exam);

        System.out.println("Exam A started by Student 1.");
        System.out.println(attempt.answer(q1, "C"));
        System.out.println(attempt.answer(q2, "True"));
        System.out.println(attempt.submit());
        System.out.println(attempt.answer(q1, "A"));
    }
}
