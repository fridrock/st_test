package ru.evanemo.st_test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evanemo.st_test.dto.inner.SaveResultDto;
import ru.evanemo.st_test.model.Result;
import ru.evanemo.st_test.model.Test;
import ru.evanemo.st_test.repository.ResultRepository;

import java.util.UUID;

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
}
