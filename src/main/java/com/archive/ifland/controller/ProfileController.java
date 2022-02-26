package com.archive.ifland.controller;

import com.archive.ifland.domain.Profile;
import com.archive.ifland.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

  private final ProfileRepository profileRepository;

  @GetMapping("/new")
  public String createForm(Model model) {
    model.addAttribute("profileForm", new ProfileForm());

    return "members/createProfileForm";
  }

  @PostMapping("/new")
  public String create(@Valid ProfileForm profileForm, BindingResult result) {

    if (result.hasErrors()) return "members/createProfileForm";

    Profile profile = new Profile(profileForm);

    profileRepository.save(profile);

    return "redirect:/";
  }

}
