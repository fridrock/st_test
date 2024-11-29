package ru.evanemo.st_test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.evanemo.st_test.dto.request.testgroup.GroupToTestDto;
import ru.evanemo.st_test.dto.response.test.GetTestDto;
import ru.evanemo.st_test.facade.TestGroupFacade;
import ru.evanemo.st_test.model.Test;
import ru.evanemo.st_test.model.TestGroup;
import ru.evanemo.st_test.service.TestGroupService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tests-groups")
@RequiredArgsConstructor
public class TestGroupController {
  private final TestGroupFacade testGroupFacade;
  @PostMapping
  @PreAuthorize("hasAuthority('TEACHER')")
  public ResponseEntity<TestGroup> addGroupToTest(@RequestBody GroupToTestDto dto){
    return ResponseEntity.ok(testGroupFacade.addGroupToTest(dto));
  }
  @GetMapping
  @PreAuthorize("hasAuthority('TEACHER')")
  public ResponseEntity<List<UUID>> getGroupsByTest(@RequestParam("testId") UUID testId){
    return ResponseEntity.ok(testGroupFacade.getGroupsByTest(testId));
  }
  @DeleteMapping
  @PreAuthorize("hasAuthority('TEACHER')")
  public ResponseEntity<String> deleteGroupFromTest(@RequestBody GroupToTestDto dto){
    testGroupFacade.deleteGroupFromTest(dto);
    return ResponseEntity.ok("ok");
  }
  @GetMapping("/byGroup/{groupId}")
  @PreAuthorize("hasAuthority('STUDENT')")
  public ResponseEntity<List<GetTestDto>> getTestsByGroupId(@PathVariable("groupId") UUID groupId){
    return ResponseEntity.ok(testGroupFacade.getTestsByGroupId(groupId));
  }
}
