package edu.northeastern.cs5200.jpa.repository;

import edu.northeastern.cs5200.jpa.entity.Enrollment;
import edu.northeastern.cs5200.jpa.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Integer> {

    @Query("SELECT enrollment FROM Enrollment enrollment WHERE enrollment.student.id = :studentId")
    public List<Enrollment> findEnrollmentsByStudent(@Param("studentId") Integer studentId);
}
