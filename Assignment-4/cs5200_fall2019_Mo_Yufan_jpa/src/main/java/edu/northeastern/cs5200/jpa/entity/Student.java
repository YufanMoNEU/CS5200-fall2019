package edu.northeastern.cs5200.jpa.entity;

import javax.persistence.*;
import java.util.*;

@Entity
public class Student extends Person {
    private int gradYear;
    private long scholarship;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments = new ArrayList<>();

    public Student() {
    }

    public Student(String username, String password, String firstName, String lastName) {
    	//inherate from Person class
        super(username, password, firstName, lastName);
    }

    public Student(String username, String password, String firstName, String lastName, int gradYear, long scholarship) {
        super(username, password, firstName, lastName);
        this.gradYear = gradYear;
        this.scholarship = scholarship;
    }

    public int getGradYear() {
        return gradYear;
    }

    public void setGradYear(int gradYear) {
        this.gradYear = gradYear;
    }

    public long getScholarship() {
        return scholarship;
    }

    public void setScholarship(long scholarship) {
        this.scholarship = scholarship;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void assignEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
        if (enrollment.getStudent() != this) {
            enrollment.setStudent(this);
        }
    }
}

