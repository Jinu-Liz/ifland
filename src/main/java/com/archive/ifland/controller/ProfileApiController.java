package com.archive.ifland.controller;

import com.archive.ifland.dto.ProfileCommentResponse;
import com.archive.ifland.service.ProfileService;
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

  @PostMapping("/like/plus")
  public void plusLikeCount(@RequestParam Long id) {
    profileService.plusLikeCount(id);
  }

  @PostMapping("/like/minus")
  public void minusLikeCount(@RequestParam Long id) {
    profileService.minusLikeCount(id);
  }

  @PostMapping("/comment")
  public ProfileCommentResponse comment(String content) {

    return profileService.writeComment(content);
  }
}
