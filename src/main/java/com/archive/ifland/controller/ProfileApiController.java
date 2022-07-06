package com.archive.ifland.controller;

import com.archive.ifland.service.ProfileService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/profile")
public class ProfileApiController {

  private final ProfileService profileService;

  @GetMapping("/list")
  public List<?> profileList() {

    return profileService.selectProfiles();

  }

  @PatchMapping("/like/plus")
  public void plusLikeCount(@RequestParam Long id) {
    profileService.plusLikeCount(id);
  }

  @PatchMapping("/like/minus")
  public void minusLikeCount(@RequestParam Long id) {
    profileService.minusLikeCount(id);
  }

  @PostMapping("/comment")
  public void writeComment(@RequestBody String data) {
    JsonParser parser = new JsonParser();
    JsonObject obj = parser.parse(data).getAsJsonObject();
    profileService.writeComment(obj.get("contents").getAsString());
  }
}
