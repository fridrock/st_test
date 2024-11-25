package ru.evanemo.st_test.dto.request.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateResponseDto {
  private String responseText;
  private Boolean correctness;
}
