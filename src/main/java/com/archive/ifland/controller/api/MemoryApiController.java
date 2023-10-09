package com.archive.ifland.controller.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/memorize")
public class MemoryApiController {

  @PostMapping("/comment")
  public void writeComment(@RequestBody String data) {
    JsonParser parser = new JsonParser();
    JsonObject obj = parser.parse(data).getAsJsonObject();
  }
}
