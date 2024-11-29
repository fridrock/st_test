package ru.evanemo.st_test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evanemo.st_test.dto.inner.SaveResultDto;
import ru.evanemo.st_test.exception.AlreadyExistsException;
import ru.evanemo.st_test.exception.NotFoundException;
import ru.evanemo.st_test.model.Result;
import ru.evanemo.st_test.model.Test;
import ru.evanemo.st_test.repository.ResultRepository;

import java.io.NotActiveException;
import java.util.List;
import java.util.UUID;

import static ru.evanemo.st_test.exception.AlreadyExistsException.USER_PASSED_TEST;

@Service
@RequiredArgsConstructor
public class ResultService {
  private final ResultRepository resultRepository;
  public Result saveResult(SaveResultDto dto){
    var result = Result.builder()
        .userId(dto.getUserId())
        .test(dto.getTest())
        .correctAmount(dto.getCorrect())
        .maxCorrectAmount(dto.getMaxCorrectAmount())
        .build();
    return resultRepository.save(result);
  }

  public void checkIfUserPassedThisTest(UUID studentId, UUID testId){
    resultRepository.findByUserIdAndTestId(studentId, testId)
        .ifPresent((v)-> {throw new AlreadyExistsException(USER_PASSED_TEST);});
  }

  public List<Result> getStudentResults(UUID studentId){
    return resultRepository.findByUserId(studentId);
  }

  public Result getStudentResult(UUID userId, UUID testId) {
    return resultRepository.findByUserIdAndTestId(userId, testId)
        .orElseThrow(()-> new NotFoundException(String.format(NotFoundException.STUDENT_RESULT, userId, testId)));
  }
}
