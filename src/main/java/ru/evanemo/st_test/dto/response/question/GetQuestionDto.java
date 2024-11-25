package ru.evanemo.st_test.dto.response.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.evanemo.st_test.model.Question;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetQuestionDto {
  private UUID id;
  private String questionText;
  private List<GetResponseDto> responses;
  public static GetQuestionDto fromQuestion(Question question) {
    return GetQuestionDto.builder()
        .id(question.getId())
        .questionText(question.getQuestionText())
        .responses(question.getResponses().stream().map(GetResponseDto::fromResponse).collect(Collectors.toList()))
        .build();
  }
}
