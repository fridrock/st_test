package ru.evanemo.st_test.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.evanemo.st_test.dto.request.test.CheckTestDto;
import ru.evanemo.st_test.dto.request.test.CreateTestDto;
import ru.evanemo.st_test.dto.response.result.GetResultDto;
import ru.evanemo.st_test.dto.response.test.GetTestDto;
import ru.evanemo.st_test.facade.TestFacade;
import ru.evanemo.st_test.utils.SecurityContextHolderUtils;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestController {
  private final TestFacade testFacade;

  @GetMapping
  @PreAuthorize("hasAuthority('TEACHER')")
  public ResponseEntity<List<GetTestDto>> getTestsByTeacher() {
    return ResponseEntity.ok(testFacade.getByTeacherId(SecurityContextHolderUtils.getUserId()));
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('TEACHER')")
  public ResponseEntity<GetTestDto> getById(@PathVariable("id") UUID id) {
    return ResponseEntity.ok(testFacade.getById(id));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('TEACHER')")
  public ResponseEntity<String> deleteById(@PathVariable("id") UUID id) {
    testFacade.deleteById(id);
    return ResponseEntity.ok("ok");
  }

  @PostMapping
  @PreAuthorize("hasAuthority('TEACHER')")
  public ResponseEntity<GetTestDto> createTest(@Valid @RequestBody CreateTestDto dto) {
    dto.setTeacherId(SecurityContextHolderUtils.getUserId());
    return ResponseEntity.ok(testFacade.createTest(dto));
  }
  @PostMapping("/check")
  @PreAuthorize("hasAuthority('STUDENT')")
  public ResponseEntity<GetResultDto> checkTest(@RequestBody CheckTestDto dto){
    return ResponseEntity.ok(testFacade.checkTest(dto));
  }
}
