package com.axelblaze.quizapp.repositories;

import com.axelblaze.quizapp.enums.Category;
import com.axelblaze.quizapp.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(Category category);

    Optional<Question> findByQuestionTitle(String title);

    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionByCategory(Category category, int numQ);

}
