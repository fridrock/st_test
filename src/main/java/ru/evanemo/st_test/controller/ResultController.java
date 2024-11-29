package ru.evanemo.st_test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.evanemo.st_test.dto.response.result.GetResultDto;
import ru.evanemo.st_test.facade.ResultFacade;
import ru.evanemo.st_test.utils.SecurityContextHolderUtils;

import java.util.List;
import java.util.UUID;

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
  @GetMapping("/studentResult")
  @PreAuthorize("hasAuthority('TEACHER')")
  public ResponseEntity<GetResultDto> getStudentResult(@RequestParam("userId")UUID userId, @RequestParam("testId") UUID testId){
    return ResponseEntity.ok(resultFacade.getStudentResult(userId, testId));
  }

}
