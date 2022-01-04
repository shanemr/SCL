package com.vaadin.tutorial.crm.backend.repository;

import com.vaadin.tutorial.crm.backend.entity.UserAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnswersRepository extends JpaRepository<UserAnswers,Long> {
    /*@Query("SELECT u FROM UserAnswers u WHERE u.id = :id")
    public UserAnswers search(@Param("username") String id);*/


}

