import java.time.LocalDateTime;
import java.util.*;

public class SchoolManager
{

    private AOAGroupProject mainFrame; // Reference to the main frame

    public SchoolManager(AOAGroupProject mainFrame) {
        this.mainFrame = mainFrame;
    }
    private HashMap<Integer, Student> students;
    private AVL_Tree courseTree;

    public SchoolManager() {
        students = new HashMap<>();
        courseTree = new AVL_Tree();
    }

    public void addStudent(Student student) {
        students.put(student.getStudentId(), student);
    }

    public void removeStudent(int studentId) {
        students.remove(studentId);
    }

    public Student getStudent(int studentId) {
        return students.get(studentId);
    }

    public void addCourse(Courses course) {
        courseTree.root = courseTree.insert(courseTree.root, course);
    }

    public void removeCourse(int courseId) {
        // Implement course removal logic from AVL tree if needed
    }

    public List<Courses> printCourseList() {
        return courseTree.inOrderTraversal(courseTree.root);
    }
    public void checkAndAddConflictingCourses(Courses newCourse) {
        List<Courses> conflictingCourses = new ArrayList<>();
    
        List<Courses> coursesList = courseTree.inOrderTraversal(courseTree.root);
    
        for (Courses existingCourse : coursesList) {
            // Check if the start time of the new course is between the start and end times of an existing course
            if (newCourse.getStartTime().isAfter(existingCourse.getStartTime()) &&
                newCourse.getStartTime().isBefore(existingCourse.getEndTime())) {
                conflictingCourses.add(existingCourse);
            }
            // Check if the end time of the new course is between the start and end times of an existing course
            else if (newCourse.getEndTime().isAfter(existingCourse.getStartTime()) &&
                     newCourse.getEndTime().isBefore(existingCourse.getEndTime())) {
                conflictingCourses.add(existingCourse);
            }
            // Check if the new course completely overlaps with an existing course
            else if (newCourse.getStartTime().isBefore(existingCourse.getStartTime()) &&
                     newCourse.getEndTime().isAfter(existingCourse.getEndTime())) {
                conflictingCourses.add(existingCourse);
            }
        }
    
        // Add the conflicting courses to the new course
        for (Courses conflictingCourse : conflictingCourses) {
            newCourse.addConflictingCourseId(conflictingCourse.getCourseId());
        }
    
        // Add the new course to the course list
        addCourse(newCourse);
    }
   
    
    public static void main(String[] args) {
        SchoolManager schoolManager = new SchoolManager();
    
        // Create some students and courses
        Student student1 = new Student("John Doe");
        Student student2 = new Student("Jane Smith");
    
        Courses course1 = new Courses(101, "Mathematics", LocalDateTime.of(2023, 9, 28, 10, 0), LocalDateTime.of(2023, 9, 28, 12, 0));
        Courses course2 = new Courses(102, "History", LocalDateTime.of(2023, 9, 28, 13, 0), LocalDateTime.of(2023, 9, 28, 15, 0));
    
        // Add students and courses
        schoolManager.addStudent(student1);
        schoolManager.addStudent(student2);
        schoolManager.addCourse(course1);
        schoolManager.addCourse(course2);
    
        // Print the course list
        List<Courses> courseList = schoolManager.printCourseList();
        System.out.println("Course List: " + courseList);
    
        // Add a new course that may conflict
        Courses newCourse = new Courses(103, "Physics", LocalDateTime.of(2023, 9, 28, 11, 30), LocalDateTime.of(2023, 9, 28, 13, 30));
        schoolManager.checkAndAddConflictingCourses(newCourse);
    
        // Print the updated course list with conflicting courses
        List<Courses> updatedCourseList = schoolManager.printCourseList();
        System.out.println("Updated Course List: " + updatedCourseList);
    
        // Print the conflicting courses for the new course
        System.out.println("Conflicting Courses for Course 103: " + newCourse.getConflictingCourseIds());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Menu for " + student1.getStudentName());
            System.out.println("1. Add Class");
            System.out.println("2. Drop Class");
            System.out.println("3. Check Schedule");
            System.out.println("4. View All Courses");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                // Add Class
                System.out.println("Available Courses:");
                List<Courses> allCourses = schoolManager.printCourseList();
                for (int i = 0; i < allCourses.size(); i++) {
                    System.out.println(allCourses.get(i).getCourseId() + ". " + allCourses.get(i).getCourseName());
                }
                System.out.println("Enter the class you want you add");
                int courseChoice = scanner.nextInt();
                if (courseChoice >= 1 && courseChoice <= allCourses.size()) {
                    // Get the selected course
                    Courses selectedCourse = allCourses.get(courseChoice - 1); 
            
                    // Check for conflicting courses in the student's list of courses
                    List<Integer> conflictingCourseIds = selectedCourse.getConflictingCourseIds();
            
                    if (!conflictingCourseIds.isEmpty()) 
                    {
                        System.out.println("This class conflicts with the following classes:");
            
                        for (Integer courseId : conflictingCourseIds) 
                        {
                            System.out.println(courseId);
                        }

                        System.out.println("Please choose another class or adjust your schedule.");
                    } 
                    else 
                    {
                        // Add the course to the student's list of courses
                        List<Student> addNew = new ArrayList<Student>(1);
                        addNew.add(student1);
                        student1.addCourse(selectedCourse);
                        selectedCourse.setStudents(addNew);
                        System.out.println("Added " + selectedCourse.getCourseName() + " to your classes.");
                    }
                } else {
                    System.out.println("Invalid course selection.");
                }
                break;
            
                case 2:
                    // Drop Class
                    System.out.println("Your Enrolled Classes:");
                    List<Courses> enrolledCourses = student1.getCourses();
                    if (enrolledCourses.isEmpty()) 
                    {
                        System.out.println("You are not enrolled in any classes.");
                    } 
                    else 
                    {
                        for (int i = 0; i < enrolledCourses.size(); i++) 
                        {
                            System.out.println(enrolledCourses.get(i).getCourseId() + ". " + enrolledCourses.get(i).getCourseName());
                        }
                        System.out.print("Enter the ID of the class you want to drop: ");
                        int classIdToDrop = scanner.nextInt();
                        // Find the class with the entered ID in the student's enrolled courses
                        Courses classToDrop = null;
                        for (Courses enrolledCourse : enrolledCourses) 
                        {
                            if (enrolledCourse.getCourseId() == classIdToDrop) 
                            {
                                classToDrop = enrolledCourse;
                                break;
                            }
                        }
                        if (classToDrop != null) 
                        {
                                // Remove the class from the student's enrolled courses
                                student1.removeCourse(classToDrop);
                                System.out.println("Dropped " + classToDrop.getCourseName() + " from your classes.");
                        } 
                        else 
                        {
                            System.out.println("Invalid class ID. No class with that ID found.");
                        }
                    }
                    break;

                    case 3:
                    // Check Schedule
                    List<Courses> studentCourses = student1.getCourses(); // Assuming student1 is the current student
                    
                    if (studentCourses.isEmpty()) {
                        System.out.println("You are not enrolled in any classes.");
                    } else {
                        System.out.println("Enrol1led Classes:");
                        for (Courses course : studentCourses) {
                            System.out.println(course.getCourseId() + ". " + course.getCourseName());
                        }
                    }
                    break;
                    case 4:
                    // View All Courses
                    List<Courses> allCoursesList = schoolManager.printCourseList();
                    
                    if (allCoursesList.isEmpty()) {
                        System.out.println("No courses are available.");
                    } else {
                        System.out.println("Available Courses:");
                        for (int i = 0; i < allCoursesList.size(); i++) {
                            Courses course = allCoursesList.get(i);
                            System.out.println(course.getCourseId() + ". " + course.getCourseName());
                        }
                        
                        System.out.println("Enter the course ID to view enrolled students:");
                        int courseIdToView = scanner.nextInt();
                
                        // Get the course based on the course ID
                        Courses courseToView = allCoursesList.get(courseIdToView-1);
                
                        if (courseToView != null) {
                            List<Student> enrolledStudents = courseToView.getStudents();
                            if (enrolledStudents.isEmpty()) {
                                System.out.println("No students are enrolled in this class.");
                            } else {
                                System.out.println("Enrolled Students in " + courseToView.getCourseName() + ":");
                                for (Student student : enrolledStudents) {
                                    System.out.println(student.getStudentId() + ". " + student.getStudentName());
                                }
                            }
                        } else {
                            System.out.println("Course not found.");
                        }
                    }
                    break;
                
                case 5:
                    // Exit the menu
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}