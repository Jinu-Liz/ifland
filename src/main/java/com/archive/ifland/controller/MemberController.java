package com.archive.ifland.controller;

import com.archive.ifland.dto.validator.MemberFormValidator;
import com.archive.ifland.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

  private final MemberService memberService;

  private final MemberFormValidator memberFormValidator;

  @InitBinder("memberForm")
  public void initBinder(WebDataBinder webDataBinder) { webDataBinder.addValidators(memberFormValidator); }

  @PostMapping("/new")
  public String create(@Valid MemberForm memberForm, BindingResult result) {

    if (result.hasErrors()) return "main/signup";

    memberService.createMember(memberForm);

    return "redirect:/mail/auth/verifying";
  }

}
