import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GradesMain {
    record Student(String name, List<Integer> grades){}

    public List<Student> students;

    public GradesMain() {
        students = List.of(
                new Student("Tom", List.of(95, 88, 83, 92)),        // Test that 89.5 rounds to an A
                new Student("Ted", List.of(95, 88, 82, 92)),        // Tests that 89.25 rounds down to B
                new Student("Bill", List.of(100, 100, 100, 100)),   // Tests 3-digit average
                new Student("Phil", List.of(10, 10, 10, 10)),       // Tests 10 is not confused with 100
                new Student("Lisa", List.of(90, 85, 66, 97)),
                new Student("John", List.of(89, 88, 87, 86)),
                new Student("Joe", List.of(95, 98, 94, 92)),
                new Student("Jason", List.of(82, 88, 84, 89))
        );
    }
    String findLetterGrade( double average) {
        return switch (( int ) (Math.round(average) / 10)) {
            case 9, 10 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> "F";
        };
    }
    Map<String, Integer> calcFinalGradeSummary()  {
        TreeMap<String, Integer> summary = new TreeMap<>();
        students.stream()
                .map(this :: calcAverage)                   // Calculate the average of each student's grades
                .map(this :: findLetterGrade)               // Find each student's letter grade
                .toList().forEach(g -> summary.put(g, summary.getOrDefault(g, 0) + 1)); // Summarize by letter grade
        return summary;
    }


}
