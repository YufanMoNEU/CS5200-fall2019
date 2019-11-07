package edu.northeastern.cs5200.jpa.entity;

import javax.persistence.*;
import java.util.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String label;

    @ManyToOne
    private Faculty author;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Section> sections = new ArrayList<>();

    public Course() {
    }

    public Course(String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Faculty getAuthor() {
        return author;
    }

    public void setAuthor(Faculty author) {
        this.author = author;
        if (!author.getAuthoredCourses().contains(this)) {
            author.getAuthoredCourses().add(this);
        }
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void assignSection(Section section) {
        this.sections.add(section);
        if (section.getCourse() != this) {
            section.setCourse(this);
        }
    }
}
