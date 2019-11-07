package edu.northeastern.cs5200.jpa.repository;

import edu.northeastern.cs5200.jpa.entity.Course;
import edu.northeastern.cs5200.jpa.entity.Faculty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    @Query("SELECT course FROM Course course WHERE course.label=:label")
    public Course findCourseByLabel(@Param("label") String label);

    @Query("SELECT course FROM Course course WHERE course.author=:author")
    public List<Course> findCoursesForAuthor(@Param("author") Faculty faculty);
}

