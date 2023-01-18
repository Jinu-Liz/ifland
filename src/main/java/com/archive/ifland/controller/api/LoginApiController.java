package com.archive.ifland.controller.api;

import com.archive.ifland.dto.response.AuthResponse;
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
  public AuthResponse findPassword(@RequestParam String account) {
    AuthResponse authResponse = memberService.sendEmailForNewPassword(account);
    return authResponse;

  }

  @GetMapping("/change-password")
  public AuthResponse changePassword(@RequestParam Long id,
                                     @RequestParam String newPassword) {

    AuthResponse authResponse = new AuthResponse();

    return authResponse;
//    AuthResponse authResponse = memberService.sendEmailForNewPassword(account);
//    return authResponse;
  }

}
