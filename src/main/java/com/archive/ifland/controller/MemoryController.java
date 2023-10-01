package com.archive.ifland.controller;

import com.archive.ifland.dto.MemoryDto;
import com.archive.ifland.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/memorize")
public class MemoryController {

  private final MemoryService memoryService;

  @GetMapping("/list")
  public String list(Model model) {
    model.addAttribute("cp", "memorize");

    return "main/memory";
  }

  @GetMapping("/content/{id}")
  public String content(Model model,
                        @PathVariable Long id) {

    MemoryDto memory = memoryService.selMemory(id);
    model.addAttribute("cp", "memorize");
    model.addAttribute("memory", memory);

    return "main/save-memory";

  }
}
