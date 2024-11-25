package ru.evanemo.st_test.dto.request.test;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTestDto {
  @NotBlank
  private String name;
  private UUID teacherId;
}
