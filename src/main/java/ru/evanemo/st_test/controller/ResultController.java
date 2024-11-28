package ru.evanemo.st_test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.evanemo.st_test.dto.response.result.GetResultDto;
import ru.evanemo.st_test.facade.ResultFacade;
import ru.evanemo.st_test.utils.SecurityContextHolderUtils;

import java.util.List;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class ResultController {
  private final ResultFacade resultFacade;
  @GetMapping
  @PreAuthorize("hasAuthority('STUDENT')")
  public ResponseEntity<List<GetResultDto>> getStudentResults(){
    return ResponseEntity.ok(resultFacade.getStudentResults(SecurityContextHolderUtils.getUserId()));
  }
  @GetMapping("/group/{groupId}")
  @PreAuthorize("hasAuthority('TEACHER')")
  public ResponseEntity<String> getStudentsResult(){
    return null;
  }

}
