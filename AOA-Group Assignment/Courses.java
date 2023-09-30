import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Courses {
    private int courseId;
    private String courseName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Student> Student;
    private List<Integer> conflictingCourseIds; // List to store conflicting course IDs

    public Courses(int courseId, String courseName, LocalDateTime startTime, LocalDateTime endTime) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.conflictingCourseIds = new ArrayList<>(); // Initialize the list
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Integer> getConflictingCourseIds() {
        return conflictingCourseIds;
    }

    // Method to add a conflicting course ID
    public void addConflictingCourseId(int courseId) {
        conflictingCourseIds.add(courseId);
    }

    // Method to remove a conflicting course ID
    public void removeConflictingCourseId(int courseId) {
        conflictingCourseIds.remove(Integer.valueOf(courseId));
    }
    public List<Student> getStudents() {
        return Student;
    }
    
    public void setStudents(List<Student> students) {
        this.Student = students;
    }
}
