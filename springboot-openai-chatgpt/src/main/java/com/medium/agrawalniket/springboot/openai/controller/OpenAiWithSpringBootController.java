package com.medium.agrawalniket.springboot.openai.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medium.agrawalniket.springboot.openai.dto.UserInput;
import com.medium.agrawalniket.springboot.openai.service.OpenAiWithSpringBootService;

@RestController
@RequestMapping("api/v1/openAi/springBoot")
public class OpenAiWithSpringBootController {

  @Autowired
  OpenAiWithSpringBootService service;

  @PostMapping
  public List<String> submitOpenAiRequest(@RequestBody UserInput userInput) {
    return service.submitOpenAiRequest(userInput.getUserCommand());
  }

}
