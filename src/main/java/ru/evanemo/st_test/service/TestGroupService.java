package ru.evanemo.st_test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evanemo.st_test.dto.request.testgroup.GroupToTestDto;
import ru.evanemo.st_test.exception.AlreadyExistsException;
import ru.evanemo.st_test.exception.NotFoundException;
import ru.evanemo.st_test.model.TestGroup;
import ru.evanemo.st_test.repository.TestGroupRepository;
import ru.evanemo.st_test.repository.TestRepository;


import static ru.evanemo.st_test.exception.AlreadyExistsException.TEST_GROUP;
import static ru.evanemo.st_test.exception.NotFoundException.TEST_BY_ID;
import static ru.evanemo.st_test.exception.NotFoundException.TEST_GROUP_BY_ID;

@Service
@RequiredArgsConstructor
public class TestGroupService {
  private final TestGroupRepository testGroupRepository;
  private final TestRepository testRepository;

  public TestGroup addGroupToTest(GroupToTestDto dto) {
    testRepository.findById(dto.getTestId()).orElseThrow(
        ()->new NotFoundException(String.format(TEST_BY_ID, dto.getTestId())));
    testGroupRepository.findByTestIdAndGroupId(dto.getTestId(), dto.getGroupId()).ifPresent((v)->{
      throw new AlreadyExistsException(String.format(TEST_GROUP, v.getTestId(), v.getGroupId()));
    });


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
}
