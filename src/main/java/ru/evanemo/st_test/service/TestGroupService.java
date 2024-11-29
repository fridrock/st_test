package ru.evanemo.st_test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evanemo.st_test.dto.request.testgroup.GroupToTestDto;
import ru.evanemo.st_test.dto.response.test.GetTestDto;
import ru.evanemo.st_test.exception.AlreadyExistsException;
import ru.evanemo.st_test.exception.NotFoundException;
import ru.evanemo.st_test.model.Test;
import ru.evanemo.st_test.model.TestGroup;
import ru.evanemo.st_test.repository.TestGroupRepository;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static ru.evanemo.st_test.exception.AlreadyExistsException.TEST_GROUP;
import static ru.evanemo.st_test.exception.NotFoundException.TEST_GROUP_BY_ID;

@Service
@RequiredArgsConstructor
public class TestGroupService {
  private final TestGroupRepository testGroupRepository;
  private final TestService testService;

  public TestGroup addGroupToTest(GroupToTestDto dto) {
    //or else throw not found
    if(testService.getById(dto.getTestId())!=null) {
      testGroupRepository.findByTestIdAndGroupId(dto.getTestId(), dto.getGroupId()).ifPresent((v) -> {
        throw new AlreadyExistsException(String.format(TEST_GROUP, v.getTestId(), v.getGroupId()));
      });
    }

    var testGroup = TestGroup.builder()
        .testId(dto.getTestId())
        .groupId(dto.getGroupId())
        .build();
    return testGroupRepository.save(testGroup);
  }

  public void deleteGroupFromTest(GroupToTestDto dto){
    var testGroup = testGroupRepository.findByTestIdAndGroupId(dto.getTestId(), dto.getGroupId()).orElseThrow(
        ()->new NotFoundException(String.format(TEST_GROUP_BY_ID, dto.getTestId(), dto.getGroupId()))
    );
    testGroupRepository.deleteById(testGroup.getId());
  }
  public List<Test> getTestsByGroupId(UUID groupId){
    var testGroups = testGroupRepository.findByGroupId(groupId);
    return testGroups.stream().map(testGroup-> testService.getById(testGroup.getTestId())).collect(Collectors.toList());
  }

  public List<UUID> getGroupsByTest(UUID testId) {
    return testGroupRepository.findByTestId(testId).stream().map(TestGroup::getGroupId).collect(Collectors.toList());
  }
}
