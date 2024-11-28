package ru.evanemo.st_test.dto.inner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.evanemo.st_test.model.Test;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveResultDto {
  private UUID userId;
  private Test test;
  private Integer correct;
  private Integer maxCorrectAmount;
}
