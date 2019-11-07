package edu.northeastern.cs5200.jpa.repository;

import edu.northeastern.cs5200.jpa.entity.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SectionRepository extends CrudRepository<Section, Integer> {

    @Query("SELECT section FROM Section section WHERE section.title=:title")
    public Section findSectionByTitle(@Param("title") String title);
}
