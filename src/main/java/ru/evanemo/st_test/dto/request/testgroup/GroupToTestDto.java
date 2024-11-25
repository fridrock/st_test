package ru.evanemo.st_test.dto.request.testgroup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupToTestDto {
  private UUID groupId;
  private UUID testId;
}
