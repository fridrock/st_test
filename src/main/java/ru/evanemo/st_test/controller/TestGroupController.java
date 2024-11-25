package ru.evanemo.st_test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.evanemo.st_test.dto.request.testgroup.GroupToTestDto;
import ru.evanemo.st_test.model.TestGroup;
import ru.evanemo.st_test.service.TestGroupService;

@RestController
@RequestMapping("/tests-groups")
@RequiredArgsConstructor
public class TestGroupController {
  private final TestGroupService groupTestService;
  @PostMapping
  @PreAuthorize("hasAuthority('TEACHER')")
  public ResponseEntity<TestGroup> addGroupToTest(@RequestBody GroupToTestDto dto){
    return ResponseEntity.ok(groupTestService.addGroupToTest(dto));
  }
  @DeleteMapping
  @PreAuthorize("hasAuthority('TEACHER')")
  public ResponseEntity<String> deleteGroupFromTest(@RequestBody GroupToTestDto dto){
    groupTestService.deleteGroupFromTest(dto);
    return ResponseEntity.ok("ok");
  }
}
