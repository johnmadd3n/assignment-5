import org.junit.jupiter.api.Test;

import javax.xml.transform.Result;
import java.util.List;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class GradesMainTest {
    private final static GradesMain GRADES_MAIN = new GradesMain();
    public record Student(String name, int grades, String Letter){}
    private final static List<Result> results = List.of(
            new Result("Tom", 89.5, "A"),
            new Result("Ted", 89.25, "B"),
            new Result("Bill", 100.0, "A"),
            new Result("Phil", 10.0, "F"),
            new Result("Lisa", 84.5, "B"),
            new Result("John", 87.5, "B"),
            new Result("Joe", 94.75, "A"),
            new Result("Jason", 85.75, "B")
    );

    Result findResult( String name) {
        return results.stream()
                .filter(result -> result.name().equals(name))       // Search results list for matching name
                .findFirst().orElse( null);                  		   // Return match or null
    }

    @Test
    void calcAverageAndFindLetterGradeTest() {
        for ( GradesMain.Student student : GRADES_MAIN.students ) {
            double average = GRADES_MAIN.calcAverage(student);             // Calculate a student's average
            String letterGrade = GRADES_MAIN.findLetterGrade(average);     // Convert average grade to a letter grade
            Result result = findResult(student.name());             // Look up the expected student grade results
            System.out.println(result);
            if (result != null) {
                assertEquals(result.average, average);              // Test the average is as expected
                assertEquals(result.grade, letterGrade);            // Test the letter grade is as expected
            } else {
                fail();
            }
        }
    }
    @Test
    double calcAverage( Student student ) {
        return student.grades().stream()
                .reduce(0, Integer::sum) * 1.0      // Sum a student's grade
                / student.grades().size();          // Divide by number of grades for student
    }
    @Test
    void finalGradeSummaryTest() {
        Map<String, Integer> summary = GRADES_MAIN.calcFinalGradeSummary();     // Summarize letter grades
        System.out.println(summary);
        assertEquals("{A=3, B=4, F=1}", summary.toString());    // Summary as expected
    }





}