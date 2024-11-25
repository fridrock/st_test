package ru.evanemo.st_test.dto.request.question;

import jakarta.validation.constraints.NotBlank;
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
public class CreateQuestionDto {
  @NotBlank
  private String questionText;
  List<CreateResponseDto> responses;
  UUID testId;
}
