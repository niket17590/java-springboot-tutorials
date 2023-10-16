package com.medium.agrawalniket.springboot.blocking.queue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medium.agrawalniket.springboot.blocking.queue.dto.TaskDetail;
import com.medium.agrawalniket.springboot.blocking.queue.service.ExecutorServiceImpl;

@RestController
@RequestMapping(value = "api/v1/executorService")
public class TaskControllerWithExecutorService {

	@Autowired
	private ExecutorServiceImpl executorServiceImpl;

	@PostMapping(value = "submitTask")
	public String submitTask(TaskDetail taskDetail) {
		executorServiceImpl.submitTask(taskDetail);
		return "You request is submitted for processing";
	}

}
