package com.archive.ifland.controller;

import com.archive.ifland.domain.Member;
import com.archive.ifland.repository.MemberRepository;
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
@RequestMapping("/members")
public class MemberController {

  private final MemberRepository memberRepository;

  @GetMapping("/new")
  public String createForm(Model model) {
    model.addAttribute("memberForm", new MemberForm());

    return "members/createMemberForm";
  }

  @PostMapping("/new")
  public String create(@Valid MemberForm memberForm, BindingResult result) {

    if (result.hasErrors()) return "members/createMemberForm";

    Member member = new Member(memberForm.getName(), memberForm.getIflandNickName(), memberForm.getPassword());

    memberRepository.save(member);

    return "redirect:/";
  }

}
