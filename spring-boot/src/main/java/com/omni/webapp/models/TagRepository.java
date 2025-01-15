package com.omni.webapp.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Integer> {
    @Query("select t from Tag t where t.name = ?1")
    Tag findByName(String name);

    @Query("select (count(t) > 0) from Tag t where t.name = ?1")
    Boolean existsByName(String infix);

    @Query("select t from Tag t where upper(t.description) like upper(concat('%', ?1, '%'))")
    Tag findByDescriptionContainingIgnoreCase(String description);

    @Query("select t from Tag t where upper(t.description) like upper(concat('%', ?1, '%')) order by t.id")
    List<Tag> findAllByDescriptionContainingIgnoreCaseOrderById(String description);

}
