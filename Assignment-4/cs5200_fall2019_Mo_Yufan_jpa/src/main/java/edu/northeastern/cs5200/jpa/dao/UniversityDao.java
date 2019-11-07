package edu.northeastern.cs5200.jpa.dao;

import edu.northeastern.cs5200.jpa.entity.*;
import edu.northeastern.cs5200.jpa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UniversityDao {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // removes all the data from the database. Note that you might need to remove records in a particular order
    public void truncateDatabase(){
        enrollmentRepository.deleteAll();
        sectionRepository.deleteAll();
        courseRepository.deleteAll();
        personRepository.deleteAll();
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Section createSection(Section section) {
        return sectionRepository.save(section);
    }

    public Section addSectionToCourse(Section section, Course course) {
        section.setCourse(course);
        return sectionRepository.save(section);
    }

    public Course setAuthorForCourse(Faculty faculty, Course course) {
        course.setAuthor(faculty);
        return courseRepository.save(course);
    }

    // enrolls a student in a section updating the number of seats available and returning true.
    // If the current available seats is zero then the enrollment is refused and method returns false
    public Boolean enrollStudentInSection(Student student, Section section) {
        if (section.getSeats() == 0) {
            return false;
        } else {
            Enrollment enrollment = new Enrollment(student, section);
            student.assignEnrollment(enrollment);
            section.assignEnrollment(enrollment);
            section.setSeats(section.getSeats() - 1);
            enrollmentRepository.save(enrollment);
            sectionRepository.save(section);
            return true;
        }
    }

    public List<Person> findAllUsers() {
        return (List<Person>) personRepository.findAll();
    }

    public List<Faculty> findAllFaculty() {
        return (List<Faculty>) facultyRepository.findAll();
    }

    public List<Student> findAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    public List<Course> findAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    public List<Section> findAllSections() {
        return (List<Section>) sectionRepository.findAll();
    }

    public List<Course> findCourseForAuthor(Faculty faculty) {
        return faculty.getAuthoredCourses();
    }

    public List<Section> findSectionForCourse(Course course) {
        return course.getSections();
    }

    public List<Student> findStudentsInSection(Section section) {
        List<Student> students = new ArrayList<>();
        List<Enrollment> enrollments = section.getEnrollments();

        for (Enrollment enrollment : enrollments) {
            students.add(enrollment.getStudent());
        }
        return students;
    }

    public List<Section> findSectionsForStudent(Student student) {
        List<Section> sections = new ArrayList<>();
        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentsByStudent(student.getId());
        for (Enrollment enrollment : enrollments) {
            sections.add(enrollment.getSection());
        }
        return sections;
    }

}
