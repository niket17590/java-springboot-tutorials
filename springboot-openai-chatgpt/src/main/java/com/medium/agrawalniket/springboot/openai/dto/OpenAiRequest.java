package com.medium.agrawalniket.springboot.openai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpenAiRequest {

	private String model;
	private String prompt;
	@JsonProperty("max_tokens")
	private Integer maxTokens;
	private Double temperature;
	@JsonProperty("top_p")
	private Double topP;
}
