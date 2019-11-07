package edu.northeastern.cs5200.jpa.dao;

import edu.northeastern.cs5200.jpa.entity.Person;
import edu.northeastern.cs5200.jpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ValidateDao {

    @Autowired
    private PersonRepository personRepository;


}