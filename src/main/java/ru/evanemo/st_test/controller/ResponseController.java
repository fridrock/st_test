package ru.evanemo.st_test.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.evanemo.st_test.dto.request.question.AddResponseDto;
import ru.evanemo.st_test.dto.response.question.GetResponseDto;
import ru.evanemo.st_test.facade.ResponseFacade;

import java.util.UUID;

@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
public class ResponseController {
  private final ResponseFacade responseFacade;
  @PostMapping
  @PreAuthorize("hasAuthority('TEACHER')")
  public ResponseEntity<GetResponseDto> addResponse(@RequestBody AddResponseDto dto){
    return ResponseEntity.ok(responseFacade.addResponse(dto));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('TEACHER')")
  public ResponseEntity<String> deleteResponse(@PathVariable("id") UUID id){
    responseFacade.deleteResponse(id);
    return ResponseEntity.ok("ok");
  }
}
