package ru.evanemo.st_test.dto.request.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckTestDto {
  private UUID testId;
  private UUID userId;
  private List<UserQuestionResponse> questionResponses;
}
