package ru.evanemo.st_test.dto.response.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.evanemo.st_test.dto.response.question.GetQuestionDto;
import ru.evanemo.st_test.model.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTestDto {
  private UUID id;
  private UUID teacherId;
  private String name;
  private List<GetQuestionDto> questions;
  public static GetTestDto fromTest(Test test){
    List<GetQuestionDto> questionDtos;
    if(test.getQuestions()!=null){
      questionDtos = test.getQuestions().stream().map(GetQuestionDto::fromQuestion).collect(Collectors.toList());
    }else{
      questionDtos = Collections.emptyList();
    }
    return new GetTestDto(test.getId(), test.getTeacherId(), test.getName(), questionDtos);
  }
}
