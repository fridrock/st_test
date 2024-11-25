package ru.evanemo.st_test.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.evanemo.st_test.dto.request.question.CreateQuestionDto;
import ru.evanemo.st_test.dto.response.question.GetQuestionDto;
import ru.evanemo.st_test.service.QuestionService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class QuestionFacade {
  private final QuestionService questionService;
  public GetQuestionDto createQuestion(CreateQuestionDto dto){
    return GetQuestionDto.fromQuestion(questionService.createQuestion(dto));
  }
  public void deleteQuestion(UUID id){
    questionService.deleteQuestionById(id);
  }
}
