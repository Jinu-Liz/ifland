package com.archive.ifland.controller;

import com.archive.ifland.domain.Profile;
import com.archive.ifland.dto.PageDto;
import com.archive.ifland.dto.ProfileDto;
import com.archive.ifland.repository.ProfileRepository;
import com.archive.ifland.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

  private final ProfileRepository profileRepository;
  private final ProfileService profileService;

  @GetMapping("/new")
  public String createForm(Model model) {
    model.addAttribute("profileForm", new ProfileForm());

    return "members/createProfileForm";
  }

  @PostMapping("/new")
  public String create(@Valid ProfileForm profileForm, BindingResult result) {

    if (result.hasErrors()) return "members/createProfileForm";

    Profile checkProfile = profileRepository.findByIflandNickName(profileForm.getIflandNickName());

    if (ObjectUtils.isEmpty(checkProfile)) {
      Profile profile = new Profile(profileForm);
      profileRepository.save(profile);
    } else {
      FieldError fieldError = new FieldError("profileForm", "iflandNickName", "이미 존재하는 아이디 입니다.");
      result.addError(fieldError);

      return "members/createProfileForm";
    }

    return "redirect:/";
  }

  @GetMapping("/comment")
  public String comment() {
    return "comment";
  }

  @GetMapping("/detail")
  public String detail() {

    return "main/anime-details";
  }

  @GetMapping("/category")
  public String category(Model model,
                         @PageableDefault(size = 1) Pageable pageable,
                         @RequestParam(required = false, defaultValue = "0", value = "page") int page) {

    Page<ProfileDto> profilePage = profileService.iflanderList(pageable);
    PageDto pageDto = new PageDto(profilePage, pageable);
    model.addAttribute("profileList", profilePage.getContent());
    model.addAttribute("pageDto", pageDto);

    return "main/categories";

  }

}
