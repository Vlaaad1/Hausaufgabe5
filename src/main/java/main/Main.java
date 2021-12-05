package main;

import controller.Controller;
import repository.CourseJDBC_Repository;
import repository.EnrollmentJDBC_Repository;
import repository.StudentJDBC_Repository;
import repository.TeacherJDBC_Repository;
import view.UI;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        CourseJDBC_Repository courseJDBCRepository = new CourseJDBC_Repository();
        StudentJDBC_Repository studentJDBCRepository = new StudentJDBC_Repository();
        TeacherJDBC_Repository teacherJDBCRepository = new TeacherJDBC_Repository();
        EnrollmentJDBC_Repository enrollmentJDBCRepository = new EnrollmentJDBC_Repository();
        Controller controller = new Controller(courseJDBCRepository, studentJDBCRepository,
                teacherJDBCRepository, enrollmentJDBCRepository);
        UI ui = new UI(controller);
        ui.display();


    }
}