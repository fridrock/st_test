package ru.evanemo.st_test.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.evanemo.st_test.dto.request.testgroup.GroupToTestDto;
import ru.evanemo.st_test.dto.response.test.GetTestDto;
import ru.evanemo.st_test.model.TestGroup;
import ru.evanemo.st_test.service.TestGroupService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TestGroupFacade {
  private final TestGroupService testGroupService;
  public TestGroup addGroupToTest(GroupToTestDto dto){
    return testGroupService.addGroupToTest(dto);
  }
  public void deleteGroupFromTest(GroupToTestDto dto){
    testGroupService.deleteGroupFromTest(dto);
  }
  public List<GetTestDto> getTestsByGroupId(UUID groupId){
    return testGroupService.getTestsByGroupId(groupId).stream()
        .map(GetTestDto::fromTest).collect(Collectors.toList());
  }

  public List<UUID> getGroupsByTest(UUID testId) {
    return testGroupService.getGroupsByTest(testId);
  }
}

