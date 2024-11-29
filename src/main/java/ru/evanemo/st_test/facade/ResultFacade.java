package ru.evanemo.st_test.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.evanemo.st_test.dto.response.result.GetResultDto;
import ru.evanemo.st_test.service.ResultService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ResultFacade {
  private final ResultService resultService;
  public List<GetResultDto> getStudentResults(UUID studentId){
    return resultService.getStudentResults(studentId).stream()
        .map(GetResultDto::fromResult).collect(Collectors.toList());
  }

  public GetResultDto getStudentResult(UUID userId, UUID testId) {
    return GetResultDto.fromResult(resultService.getStudentResult(userId, testId));
  }
}
