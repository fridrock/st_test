package ru.evanemo.st_test.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.evanemo.st_test.dto.request.test.CheckTestDto;
import ru.evanemo.st_test.dto.request.test.CreateTestDto;
import ru.evanemo.st_test.dto.response.result.GetResultDto;
import ru.evanemo.st_test.dto.response.test.GetTestDto;
import ru.evanemo.st_test.exception.AlreadyExistsException;
import ru.evanemo.st_test.model.Result;
import ru.evanemo.st_test.service.ResultService;
import ru.evanemo.st_test.service.TestService;
import ru.evanemo.st_test.utils.SecurityContextHolderUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static ru.evanemo.st_test.exception.AlreadyExistsException.USER_PASSED_TEST;

@Component
@RequiredArgsConstructor
public class TestFacade {
  private final TestService testService;
  private final ResultService resultService;
  public GetTestDto createTest(CreateTestDto dto) {
    return GetTestDto.fromTest(testService.createTest(dto));
  }
  public List<GetTestDto> getByTeacherId(UUID teacherId){
    return testService.getByTeacherId(teacherId)
        .stream().map(GetTestDto::fromTest).collect(Collectors.toList());
  }
  public GetTestDto getById(UUID testId){
    return GetTestDto.fromTest(testService.getById(testId));
  }
  public void deleteById(UUID testId){
    testService.deleteById(testId);
  }
  public GetResultDto checkTest(CheckTestDto dto){
    dto.setUserId(SecurityContextHolderUtils.getUserId());
    return GetResultDto.fromResult((testService.checkTest(dto)));
  }
}
