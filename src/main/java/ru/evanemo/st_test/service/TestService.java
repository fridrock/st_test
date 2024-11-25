package ru.evanemo.st_test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evanemo.st_test.dto.request.test.CreateTestDto;
import ru.evanemo.st_test.dto.response.test.GetTestDto;
import ru.evanemo.st_test.exception.NotFoundException;
import ru.evanemo.st_test.model.Test;
import ru.evanemo.st_test.repository.TestRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static ru.evanemo.st_test.exception.NotFoundException.TEST_BY_ID;

@Service
@RequiredArgsConstructor
public class TestService {
  private final TestRepository testRepository;
  public GetTestDto createTest(CreateTestDto dto){
    var test = Test.builder()
        .name(dto.getName())
        .teacherId(dto.getTeacherId())
        .build();
    return GetTestDto.fromTest(testRepository.save(test));
  }
  public List<GetTestDto> getByTeacherId(UUID teacherId){
    return testRepository.findByTeacherId(teacherId).stream().map(GetTestDto::fromTest).collect(Collectors.toList());
  }

  public GetTestDto getById(UUID id){
    var test = testRepository.findById(id).orElseThrow(()->new NotFoundException(String.format(TEST_BY_ID, id)));
    return GetTestDto.fromTest(test);
  }

  public void deleteById(UUID id){
    testRepository.deleteById(id);
  }
}

