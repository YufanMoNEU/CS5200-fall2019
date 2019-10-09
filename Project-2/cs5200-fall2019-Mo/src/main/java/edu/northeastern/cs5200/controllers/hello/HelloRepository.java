package edu.northeastern.cs5200.controllers.hello;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository
extends JpaRepository<HelloObject, Integer> {
}
//Once the JpaRepository has been declared, it can be used to save and retrieve records from the database.

//public class HelloRepository {
//
//}
