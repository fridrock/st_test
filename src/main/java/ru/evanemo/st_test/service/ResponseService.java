package ru.evanemo.st_test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evanemo.st_test.dto.request.question.AddResponseDto;
import ru.evanemo.st_test.exception.NotFoundException;
import ru.evanemo.st_test.model.Response;
import ru.evanemo.st_test.model.Result;
import ru.evanemo.st_test.repository.QuestionRepository;
import ru.evanemo.st_test.repository.ResponseRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResponseService {
  private final ResponseRepository responseRepository;
  private final QuestionRepository questionRepository;
  public Response addResponse(AddResponseDto dto){
    var question = questionRepository.findById(dto.getQuestionId()).orElseThrow(
        ()->new NotFoundException(String.format(NotFoundException.QUESTION_BY_ID, dto.getQuestionId()))
    );
    var response = Response.builder()
        .correctness(dto.getCorrectness())
        .responseText(dto.getResponseText())
        .question(question)
        .build();
    response = responseRepository.save(response);
    if(question.getResponses()==null){
      question.setResponses(List.of(response));
    }else{
      question.getResponses().add(response);
    }
    questionRepository.save(question);
    return response;
  }
  public void deleteResponse(UUID id){
    responseRepository.findById(id).orElseThrow(
        ()->new NotFoundException(String.format(NotFoundException.RESPONSE_BY_ID, id))
    );
    responseRepository.deleteById(id);
  }


}
