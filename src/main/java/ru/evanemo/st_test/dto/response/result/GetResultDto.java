package ru.evanemo.st_test.dto.response.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.evanemo.st_test.model.Result;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetResultDto {
  private UUID testId;
  private String testName;
  private UUID userId;
  private Integer correct;
  private Integer maxCorrect;
  public static GetResultDto fromResult(Result result){
    return GetResultDto.builder()
        .testId(result.getTest().getId())
        .testName(result.getTest().getName())
        .userId(result.getUserId())
        .correct(result.getCorrectAmount())
        .maxCorrect(result.getMaxCorrectAmount())
        .build();
  }
}
