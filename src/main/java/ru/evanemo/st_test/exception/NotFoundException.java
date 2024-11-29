package ru.evanemo.st_test.exception;


public class NotFoundException extends RuntimeException{
  public final static String TEST_BY_ID = "test with id: %s not found";
  public final static String TEST_GROUP_BY_ID = "test-group with test_id: %s and group_id: %s not found";
  public final static String QUESTION_BY_ID = "question with id: %s not found";
  public final static String RESPONSE_BY_ID = "response with id: %s not found";
  public final static String STUDENT_RESULT = "result with student_id: %s and test_id: %s not found";
  public NotFoundException(String message){
    super(message);
  }
}
