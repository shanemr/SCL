package com.vaadin.tutorial.crm.backend.repository;

import com.vaadin.tutorial.crm.backend.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answers, Long> {
    @Query("select c from Answers c " +
            "where lower(c.answer) like lower(concat('%', :searchTerm, '%')) ")
    List<Answers> search(@Param("searchTerm") String searchTerm);
}
