package com.omni.webapp.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Integer> {
    @Query("select t from Tag t where t.name = ?1")
    Tag findByName(String name);

    @Query("select count(t) > 0 from Tag t where t.name = ?1")
    Boolean findByNameNotNull(String infix);

    @Query("select t from Tag t where upper(t.description) like upper(concat('%', ?1, '%'))")
    Tag findByDescriptionContainingIgnoreCase(String description);

}
