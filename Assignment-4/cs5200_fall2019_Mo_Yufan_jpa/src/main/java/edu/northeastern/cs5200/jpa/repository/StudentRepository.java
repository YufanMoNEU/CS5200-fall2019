package edu.northeastern.cs5200.jpa.repository;

import edu.northeastern.cs5200.jpa.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Query("SELECT person FROM Person person WHERE person.username=:username")
    public Student findPersonByUsername(@Param("username") String username);

    @Query("SELECT person FROM Person person WHERE person.username=:username AND person.password=:password")
    public Student findPersonByCredentials(@Param("username") String username, @Param("password") String password);

}
