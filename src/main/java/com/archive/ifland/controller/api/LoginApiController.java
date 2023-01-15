package com.archive.ifland.controller.api;

import com.archive.ifland.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginApiController {

  private final MemberService memberService;

  @GetMapping("/find-password")
  public void findPassword(@RequestParam String account) {
    memberService.sendEmailForNewPassword(account);

  }

}
