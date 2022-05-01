package com.archive.ifland.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommonUtilsTest {

  @Test
  public void makeRandomCode() {
    String code = RandomStringUtils.random(20, 33, 125, true, true);

    System.out.println(code);
  }
}
