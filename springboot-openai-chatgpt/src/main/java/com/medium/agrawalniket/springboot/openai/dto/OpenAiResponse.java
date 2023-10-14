package com.medium.agrawalniket.springboot.openai.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OpenAiResponse {

	private String id;
	private String object;
	private LocalDate created;
	private String model;
	private List<Choice> choices;
	private Usage usage;
}
