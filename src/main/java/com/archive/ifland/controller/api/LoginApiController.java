package com.archive.ifland.controller.api;

import com.archive.ifland.dto.response.AuthResponse;
import com.archive.ifland.service.MemberService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginApiController {

  private final MemberService memberService;

  @GetMapping("/find-password")
  public AuthResponse findPassword(@RequestParam String account) {

    return memberService.sendEmailForNewPassword(account);
  }

  @PostMapping("/change-password")
  public AuthResponse changePassword(@RequestBody String param) {
    JsonParser jsonParser = new JsonParser();
    JsonObject obj = jsonParser.parse(param).getAsJsonObject();
    Long id = obj.get("id").getAsLong();
    String newPassword = obj.get("password").getAsString();

    return memberService.changePassword(id, newPassword);
  }

}
