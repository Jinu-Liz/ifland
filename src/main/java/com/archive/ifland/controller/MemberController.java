package com.archive.ifland.controller;

import com.archive.ifland.service.MemberService;
import com.archive.ifland.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/new")
  public String create(@Valid MemberForm memberForm, BindingResult result) {

    if (result.hasErrors()) return "main/signup";

    memberService.createMember(memberForm);

    return "redirect:/";
  }

}
