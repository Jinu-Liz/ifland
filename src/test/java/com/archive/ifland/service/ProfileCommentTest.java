package com.archive.ifland.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class ProfileCommentTest {

  @Test
  void witeTime() {
    String createdTime = "2022-07-01 04:13:58";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime curTime = LocalDateTime.now();
    LocalDateTime writeTime = LocalDateTime.parse(createdTime, formatter);

    int yearAgo = curTime.getYear() - writeTime.getYear();
    int monthAgo = curTime.getMonthValue() - writeTime.getMonthValue();
    int dayAgo = curTime.getDayOfMonth() - writeTime.getDayOfMonth();
    int hourAgo = curTime.getHour() - writeTime.getHour();

    System.out.println("년 차이 : " + yearAgo);
    System.out.println("달 차이 : " + monthAgo);
    System.out.println("일 차이 : " + dayAgo);
    System.out.println("시간 차이 : " + hourAgo);

    String result = "";
    if (yearAgo == 0) {
      result = monthAgo + "개월 전";
      if (monthAgo == 0) {
        result = dayAgo + "일 전";
        if (dayAgo == 0) {
          result = hourAgo + "시간 전";
        } else {
          result = dayAgo + "일 전";
        }
      } else {
        result = monthAgo + "개월 전";
      }
    } else {
      result = yearAgo + "년 전";
    }

    System.out.println(result);
  }
}
