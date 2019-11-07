package edu.northeastern.cs5200.jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private int seats;

    @OneToMany(mappedBy = "section", fetch = FetchType.EAGER)
    private List<Enrollment> enrollments = new ArrayList<>();

    @ManyToOne()
    private Course course;

    public Section() {
    }

    public Section(String title, int seats) {
        this.title = title;
        this.seats = seats;
    }

    public Section(String title, int seats, Course course) {
        this.title = title;
        this.seats = seats;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
        if (!course.getSections().contains(this)) {
            course.getSections().add(this);
        }
    }

    public void assignEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
        if (enrollment.getSection() != this) {
            enrollment.setSection(this);
        }
    }
}
