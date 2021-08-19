package com.vaadin.tutorial.crm.backend.repository;

import com.vaadin.tutorial.crm.backend.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    @Query("select c from Questions c " +
            "where lower(c.question) like lower(concat('%', :searchTerm, '%')) ")
    List<Questions> search(@Param("searchTerm") String searchTerm);
}
