

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class AOAGroupProject extends JFrame {

    private JButton studentButton;
    private JButton adminButton;

    private SchoolManager schoolManager; // Reference to the SchoolManager instance
    private JTextArea outputArea;

    public AOAGroupProject() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        schoolManager = new SchoolManager(); // Create an instance of SchoolManager
        Student student1 = new Student("John Doe");
        Student student2 = new Student("Jane Smith");

        Courses course1 = new Courses(101, "Mathematics", LocalDateTime.now(), LocalDateTime.now());
        Courses course2 = new Courses(102, "History", LocalDateTime.now(), LocalDateTime.now());

        // Add students and courses
        schoolManager.addStudent(student1);
        schoolManager.addStudent(student2);
        schoolManager.addCourse(course1);
        schoolManager.addCourse(course2);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        studentButton = new JButton("Student");
        adminButton = new JButton("Admin");

        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openStudentPage();
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAdminPage();
            }
        });

        panel.add(studentButton);
        panel.add(adminButton);

        add(panel);
        setVisible(true);
    }

    public void updateOutput(String message) {
        outputArea.setText(message);
    }

        private void openStudentPage() {
        JFrame studentFrame = new JFrame("Student Page");
        studentFrame.setSize(400, 300);
        studentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        studentFrame.setLocationRelativeTo(null);

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new GridLayout(3, 1));

        JButton addButton = new JButton("Add");
        JButton dropButton = new JButton("Drop");
        JButton roasterButton = new JButton("Class Schedule");

        JTextArea outputArea = new JTextArea(); // Create a JTextArea for output

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform some action, and update the output text
                outputArea.setText("Add button clicked.");
            }
        });

        dropButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform some action, and update the output text
                outputArea.setText("Drop button clicked.");
            }
        });

        roasterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform some action, and update the output text
                outputArea.setText("Class Schedule button clicked.");
            }
        });

        studentPanel.add(addButton);
        studentPanel.add(dropButton);
        studentPanel.add(roasterButton);

        studentFrame.add(studentPanel);
        studentFrame.add(outputArea, BorderLayout.SOUTH); // Add the output area to the frame
        studentFrame.setVisible(true);
    }


    private void openAdminPage() 
    {
        JFrame adminFrame = new JFrame("Admin Page");
        adminFrame.setSize(400, 300);
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setLocationRelativeTo(null);

        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new GridLayout(3, 1));

        JButton addCourseButton = new JButton("Add Course");
        JButton removeCourseButton = new JButton("Remove Course");
        JButton courseRoasterButton = new JButton("Class Schedule");

        outputArea = new JTextArea(); // Create a JTextArea for output

        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform some action, and update the output text
                updateOutput("Add Course button clicked.");
            }
        });

        removeCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform some action, and update the output text
                updateOutput("Remove Course button clicked.");
            }
        });

        courseRoasterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve class schedule data from SchoolManager
                //List<String> new = new ArrayList<String>;
                String scheduleData = schoolManager.printCourseList().toString();
                
                // Update the output area with the retrieved data
                updateOutput(scheduleData);
            }
        });

        adminPanel.add(addCourseButton);
        adminPanel.add(removeCourseButton);
        adminPanel.add(courseRoasterButton);

        adminFrame.add(adminPanel);
        adminFrame.add(outputArea, BorderLayout.SOUTH); // Add the output area to the frame
        adminFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AOAGroupProject();
            }
        });
    }
}
