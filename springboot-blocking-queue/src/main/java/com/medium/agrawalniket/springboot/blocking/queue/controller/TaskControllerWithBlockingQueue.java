package com.medium.agrawalniket.springboot.blocking.queue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medium.agrawalniket.springboot.blocking.queue.dto.TaskDetail;
import com.medium.agrawalniket.springboot.blocking.queue.service.TaskExecutionService;

@RestController
@RequestMapping(value = "api/v1/customQueue")
public class TaskControllerWithBlockingQueue {

	@Autowired
	private TaskExecutionService taskExecutionService;

	@PostMapping(value = "submitTask")
	public String submitTask(TaskDetail taskDetail) {
		taskExecutionService.submitTaskInQueue(taskDetail);
		return "You request is submitted for processing";
	}

	@GetMapping("getQueueSize")
	public String getQueueSize() {
		return "Total Tasks available in Queue are : " + taskExecutionService.getQueueSize();
	}

	@GetMapping("getTasksInQueue")
	public List<TaskDetail> getTasksInQueue() {
		return taskExecutionService.getQueueDetails();
	}

}
