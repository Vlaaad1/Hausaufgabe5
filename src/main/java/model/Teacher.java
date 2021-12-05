package model;

import java.util.Objects;

public class Teacher extends Person{

    private int teacherID;

    public Teacher(String firstName, String lastName, int teacherID) {
        super(firstName, lastName);
        this.teacherID = teacherID;
    }

    public int getTeacherID() { return teacherID; }
    public void setTeacherID(int teacherID) { this.teacherID = teacherID; }

    @Override
    public String toString() {
        return "Teacher{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", teacherID=" + teacherID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher teacher)) return false;
        if (!super.equals(o)) return false;
        return getTeacherID() == teacher.getTeacherID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTeacherID());
    }
}