package com.medium.agrawalniket.springboot.openai.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.medium.agrawalniket.springboot.openai.dto.OpenAiRequest;
import com.medium.agrawalniket.springboot.openai.dto.OpenAiResponse;

@Service
public class OpenAiWithSpringBootService {

	@Value("${openAi.model}")
	private String openAiModel;

	@Value("${openAi.url.endpoint}")
	private String openAiEndpoint;

	@Autowired
	@Qualifier("customRestTemplate")
	private RestTemplate restTemplate;

	private static final Double TEMPERATURE = 0.3;

	private static final Double TOP_P = 1.0;

	private static final Integer MAX_TOKENS = 60;

	public List<String> submitOpenAiRequest(String inputCommand) {
		OpenAiRequest aiRequest = OpenAiRequest.builder().maxTokens(MAX_TOKENS).temperature(TEMPERATURE).topP(TOP_P)
				.model(openAiModel).prompt(inputCommand).build();

		ResponseEntity<OpenAiResponse> restCallResponse = this.restTemplate.postForEntity(openAiEndpoint, aiRequest,
				OpenAiResponse.class);

		if (HttpStatus.OK.equals(restCallResponse.getStatusCode())) {
			OpenAiResponse aiResponse = restCallResponse.getBody();
			return aiResponse.getChoices().stream().map(choice -> choice.getText().replaceAll("[\n\r]", "")).toList();
		} else
			return Collections.singletonList("There was an Error from OpenAI Api");
	}
}
