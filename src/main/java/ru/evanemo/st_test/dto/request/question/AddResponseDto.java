package ru.evanemo.st_test.dto.request.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddResponseDto {
  private String responseText;
  private Boolean correctness;
  private UUID questionId;
}
