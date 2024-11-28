package ru.evanemo.st_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.evanemo.st_test.model.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
  List<Question> getQuestionsByTestId(UUID testId);
}
