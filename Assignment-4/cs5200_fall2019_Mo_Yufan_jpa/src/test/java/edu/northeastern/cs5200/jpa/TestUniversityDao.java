package edu.northeastern.cs5200.jpa;

import edu.northeastern.cs5200.jpa.dao.UniversityDao;
import edu.northeastern.cs5200.jpa.entity.*;
import edu.northeastern.cs5200.jpa.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;


public class TestUniversityDao extends Cs5200Fall2019MoYufanJpaApplicationTests {

    @Autowired
    UniversityDao dao;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Test
    public void testTruncateDatabase() {
        dao.truncateDatabase();
    }

    @Test
    public void testCreateFaculty() {
        Faculty alan = new Faculty("alan", "password", "Alan", "Turin", "123A", true);
        Faculty ada = new Faculty("ada", "password", "Ada", "Lovelace", "123B", true);
        dao.createFaculty(alan);
        dao.createFaculty(ada);
    }

    @Test
    public void testCreateStudent() {
        Student alice = new Student("alice", "password", "Alice", "Wonderland", 2020, 12000);
        Student bob = new Student("bob", "password", "Bob", "Hope", 2021, 23000);
        Student charlie = new Student("charlie", "password", "Charlie", "Brown", 2019, 21000);
        Student dan = new Student("dan", "password", "Dan", "Craig", 2019, 0);
        Student edward = new Student("edward", "password", "Edward", "Scissorhands", 2022, 11000);
        Student frank = new Student("frank", "password", "Fran", "Herbert", 2018, 0);
        Student gregory = new Student("gregory", "password", "Gregory", "Peck", 2023, 10000);

        dao.createStudent(alice);
        dao.createStudent(bob);
        dao.createStudent(charlie);
        dao.createStudent(dan);
        dao.createStudent(edward);
        dao.createStudent(frank);
        dao.createStudent(gregory);
    }

    @Test
    public void testCreateCourse() {
        Faculty alan = facultyRepository.findPersonByUsername("alan");
        Faculty ada = facultyRepository.findPersonByUsername("ada");
        Course a = new Course("CS1234");
        Course b = new Course("CS2345");
        Course c = new Course("CS3456");
        Course d = new Course("CS4567");
        a.setAuthor(alan);
        b.setAuthor(alan);
        c.setAuthor(ada);
        d.setAuthor(ada);
        dao.createCourse(a);
        dao.createCourse(b);
        dao.createCourse(c);
        dao.createCourse(d);
    }

    @Test
    public void testCreateSection() {
        Course ac = courseRepository.findCourseByLabel("CS1234");
        Course bc = courseRepository.findCourseByLabel("CS2345");
        Course cc = courseRepository.findCourseByLabel("CS3456");
        Section a = new Section("SEC4321", 50, ac);
        Section b = new Section("SEC5432", 50, ac);
        Section c = new Section("SEC6543", 50, bc);
        Section d = new Section("SEC7654", 50, cc);
        dao.createSection(a);
        dao.createSection(b);
        dao.createSection(c);
        dao.createSection(d);
    }

    @Test
    public void testEnrollStudentInSection() {
        Student alice = studentRepository.findPersonByUsername("alice");
        Student bob = studentRepository.findPersonByUsername("bob");
        Student charlie = studentRepository.findPersonByUsername("charlie");
        Section a = sectionRepository.findSectionByTitle("SEC4321");
        Section b = sectionRepository.findSectionByTitle("SEC5432");
        Section c = sectionRepository.findSectionByTitle("SEC6543");
        alice.setEnrollments(new ArrayList<>());
        bob.setEnrollments(new ArrayList<>());
        charlie.setEnrollments(new ArrayList<>());
        dao.enrollStudentInSection(alice, a);
        dao.enrollStudentInSection(alice, b);
        dao.enrollStudentInSection(bob, b);
        dao.enrollStudentInSection(charlie, c);
    }
}