package com.medium.agrawalniket.springboot.blocking.queue.dto;

import lombok.Data;

@Data
public class TaskDetail {

	private String recepientName;
	
	private OperationName operationName; 

	public enum OperationName {
		EMAIL, PRINT
	}
}
