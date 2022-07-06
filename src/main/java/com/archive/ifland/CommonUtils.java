package com.archive.ifland;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CommonUtils {

  // 랜덤 코드 생성
  public String makeRandomCode() {
    return RandomStringUtils.random(20, 33, 125, true, true);
  }

  // 날짜 계산
  public String getTimeAgo(String time) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    LocalDateTime curTime = LocalDateTime.now();
    LocalDateTime locTime = LocalDateTime.parse(time, formatter);

    int yearAgo = curTime.getYear() - locTime.getYear();
    int monthAgo = curTime.getMonthValue() - locTime.getMonthValue();
    int dayAgo = curTime.getDayOfMonth() - locTime.getDayOfMonth();
    int hourAgo = curTime.getHour() - locTime.getHour();

    String result = "";
    if (yearAgo == 0) {
      if (monthAgo == 0) {
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

    return result;
  }
}
