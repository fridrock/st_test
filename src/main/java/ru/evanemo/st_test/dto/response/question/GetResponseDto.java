package ru.evanemo.st_test.dto.response.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.evanemo.st_test.model.Response;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetResponseDto {
  private UUID id;
  private String responseText;
  private Boolean correctness;
  public static GetResponseDto fromResponse(Response r){
    return new GetResponseDto(r.getId(), r.getResponseText(), r.getCorrectness());
  }
}
