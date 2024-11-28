package ru.evanemo.st_test.exception;

public class AlreadyExistsException extends RuntimeException{
  public static final String TEST_GROUP = "test_group relationship with test_id: %s and group_id %s already exists";
  public static final String USER_PASSED_TEST = "user already passed this test";
  public AlreadyExistsException(String message){
    super(message);
  }
}
