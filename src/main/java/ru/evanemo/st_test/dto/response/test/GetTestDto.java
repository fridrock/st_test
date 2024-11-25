package ru.evanemo.st_test.dto.response.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.evanemo.st_test.model.Test;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTestDto {
  private UUID id;
  private UUID teacherId;
  private String name;
  public static GetTestDto fromTest(Test test){
    return new GetTestDto(test.getId(), test.getTeacherId(), test.getName());
  }
}
