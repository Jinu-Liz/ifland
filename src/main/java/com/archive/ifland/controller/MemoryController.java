package com.archive.ifland.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/memorize")
public class MemoryController {

  @GetMapping("/list")
  public String list(Model model) {
    model.addAttribute("cp", "memorize");

    return "main/memory";
  }

  @GetMapping("/content")
  public String content(Model model) {
    model.addAttribute("cp", "memorize");

    return "main/save-memory";

  }
}
