package ru.evanemo.st_test.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.evanemo.st_test.dto.request.question.CreateQuestionDto;
import ru.evanemo.st_test.dto.response.question.GetQuestionDto;
import ru.evanemo.st_test.facade.QuestionFacade;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
  private final QuestionFacade questionFacade;
  @PostMapping
  @PreAuthorize("hasAuthority('TEACHER')")
  public ResponseEntity<GetQuestionDto> createQuestion(@Valid @RequestBody CreateQuestionDto dto){
    return ResponseEntity.ok(questionFacade.createQuestion(dto));
  }
  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('TEACHER')")
  public ResponseEntity<String> deleteQuestion(@PathVariable("id") UUID id){
    questionFacade.deleteQuestion(id);
    return ResponseEntity.ok("ok");
  }
  @GetMapping
  @PreAuthorize("hasAnyAuthority('TEACHER', 'STUDENT')")
  public ResponseEntity<List<GetQuestionDto>> getQuestionsForTeacher(@RequestParam("testId") UUID testId){
    return ResponseEntity.ok(questionFacade.getTestQuestions(testId));
  }
}
