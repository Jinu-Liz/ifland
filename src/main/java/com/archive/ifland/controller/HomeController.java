package com.archive.ifland.controller;

import com.archive.ifland.dto.ProfileDto;
import com.archive.ifland.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

  private final ProfileService profileService;

  @GetMapping("/")
  public String home(Model model) {

    List<ProfileDto> profiles = profileService.selectProfiles();
    model.addAttribute("profileList", profiles);

    return "main/index";
  }

  @GetMapping("/login")
  public String login() {
    return "main/login";
  }

  @GetMapping("/sign-up")
  public String signUp() {
    return "main/signup";
  }

}
