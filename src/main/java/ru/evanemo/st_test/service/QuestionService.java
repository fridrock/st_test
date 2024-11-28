package ru.evanemo.st_test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import ru.evanemo.st_test.dto.request.question.CreateQuestionDto;
import ru.evanemo.st_test.dto.request.question.CreateResponseDto;
import ru.evanemo.st_test.dto.response.question.GetQuestionDto;
import ru.evanemo.st_test.exception.NotFoundException;
import ru.evanemo.st_test.model.Question;
import ru.evanemo.st_test.model.Response;
import ru.evanemo.st_test.model.Test;
import ru.evanemo.st_test.repository.QuestionRepository;
import ru.evanemo.st_test.repository.ResponseRepository;
import ru.evanemo.st_test.repository.TestRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static ru.evanemo.st_test.exception.NotFoundException.QUESTION_BY_ID;

@Service
@RequiredArgsConstructor
public class QuestionService {
  private final QuestionRepository questionRepository;
  private final ResponseRepository responseRepository;
  private final TestRepository testRepository;
  public Question createQuestion(CreateQuestionDto dto){
    Test testFound = testRepository.findById(dto.getTestId()).orElseThrow(
        ()->new NotFoundException(String.format(NotFoundException.TEST_BY_ID, dto.getTestId()))
    );
    var question = Question.builder()
        .questionText(dto.getQuestionText())
        .test(testFound)
        .responses(Collections.emptyList())
        .build();
    question = questionRepository.save(question);
    var responses = new ArrayList<Response>();
    for (CreateResponseDto crDto: dto.getResponses()){
      var response = Response.builder()
          .responseText(crDto.getResponseText())
          .correctness(crDto.getCorrectness())
          .question(question)
          .build();
      responses.add(responseRepository.save(response));
    }
    question.setResponses(responses);
    return questionRepository.save(question);
  }
  public void deleteQuestionById(UUID id){
    var question = questionRepository.findById(id).orElseThrow(
        ()->new NotFoundException(String.format(QUESTION_BY_ID, id))
    );
    questionRepository.deleteById(id);
  }
  public List<Question> getTestQuestions(UUID testId){
    return questionRepository.getQuestionsByTestId(testId);
  }
}
