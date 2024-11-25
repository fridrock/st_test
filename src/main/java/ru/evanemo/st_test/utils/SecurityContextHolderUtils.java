package ru.evanemo.st_test.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class SecurityContextHolderUtils {
  public static UUID getUserId(){
    return (UUID)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
