package com.archive.ifland;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

  public String makeRandomCode() {
    return RandomStringUtils.random(20, 33, 125, true, true);
  }
}
