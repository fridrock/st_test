package ru.evanemo.st_test.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.evanemo.st_test.dto.request.question.AddResponseDto;
import ru.evanemo.st_test.dto.response.question.GetResponseDto;
import ru.evanemo.st_test.service.ResponseService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ResponseFacade {
  private final ResponseService responseService;

  public GetResponseDto addResponse(AddResponseDto dto){
    return GetResponseDto.fromResponse(responseService.addResponse(dto));
  }
  public void deleteResponse(UUID id){
    responseService.deleteResponse(id);
  }
}
