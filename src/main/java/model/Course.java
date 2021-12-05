package model;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Course {

    private int courseID;
    private String name;
    private int teacherID;
    private int credits;
    private int maxEnrollment;

    public Course(int courseID, String name, int teacherID, int credits, int maxEnrollment) {
        this.courseID = courseID;
        this.name = name;
        this.teacherID = teacherID;
        this.credits = credits;
        this.maxEnrollment = maxEnrollment;
    }

    public int getCourseID() { return courseID; }
    public void setCourseID(int courseID) { this.courseID = courseID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getTeacherID() { return teacherID; }
    public void setTeacherId(int teacherID) { this.teacherID = teacherID; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public int getMaxEnrollment() { return maxEnrollment; }
    public void setMaxEnrollment(int maxEnrollment) { this.maxEnrollment = maxEnrollment;}

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", name='" + name + '\'' +
                ", teacherID=" + teacherID +
                ", credits=" + credits +
                ", maxEnrollment=" + maxEnrollment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getCourseID() == course.getCourseID() && getCredits() == course.getCredits()
                && getMaxEnrollment() == course.getMaxEnrollment()
                && Objects.equals(getName(), course.getName())
                && Objects.equals(getTeacherID(), course.getTeacherID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourseID(), getName(), getTeacherID(),
                getCredits(), getMaxEnrollment());
    }

}