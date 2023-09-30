
import java.util.*;
class Student 
{
    private String studentName;
    private int studentId;
    private List<Courses> courses;

    public Student(String studentName) 
    {
        this.studentName = studentName;
        this.courses = new ArrayList<>();
    }

    public int getStudentId()
    {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void addCourse(Courses course) {
        courses.add(course);
    }

    public void removeCourse(Courses course) {
        courses.remove(course);
    }
}
