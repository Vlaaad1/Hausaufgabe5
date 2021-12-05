package controller;

import model.Course;
import model.Student;
import model.Teacher;
import repository.CourseJDBC_Repository;
import repository.EnrollmentJDBC_Repository;
import repository.StudentJDBC_Repository;
import repository.TeacherJDBC_Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public record Controller(CourseJDBC_Repository courseRepo, StudentJDBC_Repository studentRepo, 
                         TeacherJDBC_Repository teacherRepo, EnrollmentJDBC_Repository enrolledRepo) {

    public boolean addCourse(int courseID , String name , int teacherID , int credits , int maxEnrollment) throws IOException {
        Course course = new Course(courseID , name , teacherID , credits , maxEnrollment);
        this.courseRepo.save(course);
        return true;
    }

    public boolean addStudent(String firstName , String lastName , int studentID , int totalCredits) throws IOException {
        Student student = new Student(firstName , lastName , studentID , totalCredits);
        this.studentRepo.save(student);
        return true;
    }

    public boolean addTeacher(String firstName , String lastName , int teacherID) throws IOException {
        Teacher teacher = new Teacher(firstName , lastName , teacherID);
        this.teacherRepo.save(teacher);
        return true;
    }


    public boolean register(Student student , Course course) throws IOException {
        Student foundStudent = this.studentRepo.findOne(student);
        Course foundCourse = this.courseRepo.findOne(course);
        this.enrolledRepo.save(foundStudent.getStudentID() , foundCourse.getCourseID());
        return true;
    }

    public ArrayList<Course> findAllCourses() throws IOException {
        return (ArrayList<Course>) courseRepo.findAll();
    }

    public ArrayList<Student> findAllStudents() throws IOException {
        return (ArrayList<Student>) studentRepo.findAll();
    }

    public ArrayList<Teacher> findAllTeachers() throws IOException {
        return (ArrayList<Teacher>) teacherRepo.findAll();
    }

    public boolean deleteCourse(Course course) throws IOException {
        Course foundCourse = this.courseRepo.findOne(course);
        this.enrolledRepo.deleteStudentsByCourseID(foundCourse.getCourseID());
        this.courseRepo.delete(foundCourse);
        return true;
    }

    public boolean deleteStudent(Student student) throws IOException {
        Student foundStudent = this.studentRepo.delete(student);
        this.enrolledRepo.deleteCoursesByStudentID(foundStudent.getStudentID());
        this.studentRepo.delete(foundStudent);
        return true;
    }


    public boolean deleteTeacher(Teacher teacher) throws IOException {
        Teacher foundTeacher = this.teacherRepo.findOne(teacher);
        ArrayList<Course> courses = (ArrayList<Course>) this.courseRepo.findAll();
        for (Course c : courses) {
            if (c.getTeacherID() == foundTeacher.getTeacherID()) {
                this.deleteCourse(c);
            }
        }
        this.teacherRepo.delete(foundTeacher);
        return true;
    }

    public ArrayList<Course> sortCoursesByCredits() throws IOException {
        ArrayList<Course> coursesList = (ArrayList<Course>) courseRepo.findAll();
        return (ArrayList<Course>) coursesList.stream()
                .sorted(Comparator.comparingInt(Course::getCredits))
                .collect(Collectors.toList());
    }

    public ArrayList<Course> filterCoursesByCredit(int credits) throws IOException {
        Predicate<Course> byCredits = course -> course.getCredits() < credits;
        ArrayList<Course> coursesList = (ArrayList<Course>) courseRepo.findAll();
        return (ArrayList<Course>) coursesList.stream().filter(byCredits).collect(Collectors.toList());
    }

    public ArrayList<Student> sortStudentsByCredits() throws IOException {
        ArrayList<Student> studentsList = (ArrayList<Student>) studentRepo.findAll();
        return (ArrayList<Student>) studentsList.stream()
                .sorted(Comparator.comparingInt(Student::getTotalCredits))
                .collect(Collectors.toList());
    }

    public ArrayList<Student> filterStudentsByCredits() throws IOException {
        Predicate<Student> byCredits = student -> student.getTotalCredits() < 25;
        ArrayList<Student> studentsList = (ArrayList<Student>) studentRepo.findAll();
        return (ArrayList<Student>) studentsList.stream().filter(byCredits).collect(Collectors.toList());
    }
}