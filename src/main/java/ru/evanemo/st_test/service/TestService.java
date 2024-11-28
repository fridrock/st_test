package ru.evanemo.st_test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evanemo.st_test.dto.inner.SaveResultDto;
import ru.evanemo.st_test.dto.request.test.CheckTestDto;
import ru.evanemo.st_test.dto.request.test.CreateTestDto;
import ru.evanemo.st_test.dto.request.test.UserQuestionResponse;
import ru.evanemo.st_test.exception.NotFoundException;
import ru.evanemo.st_test.model.Question;
import ru.evanemo.st_test.model.Response;
import ru.evanemo.st_test.model.Result;
import ru.evanemo.st_test.model.Test;
import ru.evanemo.st_test.repository.TestRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static ru.evanemo.st_test.exception.NotFoundException.TEST_BY_ID;

@Service
@RequiredArgsConstructor
public class TestService {
  private final TestRepository testRepository;
  private final ResultService resultService;
  public Test createTest(CreateTestDto dto){
    var test = Test.builder()
        .name(dto.getName())
        .teacherId(dto.getTeacherId())
        .build();
    return testRepository.save(test);
  }
  public List<Test> getByTeacherId(UUID teacherId){
    return testRepository.findByTeacherId(teacherId);
  }

  public Test getById(UUID id){
    return testRepository.findById(id).orElseThrow(()->new NotFoundException(String.format(TEST_BY_ID, id)));
  }

  public void deleteById(UUID id){
    testRepository.deleteById(id);
  }
  public Result checkTest(CheckTestDto dto){
    resultService.checkIfUserPassedThisTest(dto.getUserId(), dto.getTestId());
    var test = getById(dto.getTestId());
    var questions = test.getQuestions();
    List<UserQuestionResponse> userQuestionResponses = dto.getQuestionResponses();
    int maxCorrect = questions.size();
    int correct = 0;
    for(Question q : questions){
      //find response dedicated to this question
      UserQuestionResponse userQuestionResponse = userQuestionResponses.stream()
          .filter(qr->qr.getQuestionId().equals(q.getId())).findAny().orElse(null);

      if(userQuestionResponse != null){
        //find correct responses for this question
        List<Response> correctResponses = getCorrectResponses(q);
        //if all userResponses correct -> flag true
        boolean flag = true;
        for(UUID questionResponseId : userQuestionResponse.getUserResponsesIds()){
          if(!checkExistInCorrect(questionResponseId, correctResponses)){
            flag = false;
            break;
          }
        }
        if (flag && correctResponses.size() == userQuestionResponse.getUserResponsesIds().size()){
          correct+=1;
        }
      }
    }
    //saving test result
    var saveResultDto = SaveResultDto.builder()
        .userId(dto.getUserId())
        .test(test)
        .correct(correct)
        .maxCorrectAmount(maxCorrect)
        .build();
    return resultService.saveResult(saveResultDto);
  }
  private List<Response> getCorrectResponses(Question q){
    return q.getResponses().stream().filter(Response::getCorrectness).collect(Collectors.toList());
  }
  private boolean checkExistInCorrect(UUID questionResponseId, List<Response> correctResponses){
    Response r = correctResponses.stream().filter(cr->cr.getId().equals(questionResponseId)).findAny().orElse(null);
    return r!=null;
  }
}

