package com.medium.agrawalniket.springboot.blocking.queue.service;

import java.util.List;
import com.medium.agrawalniket.springboot.blocking.queue.dto.TaskDetail;

public interface TaskExecutionService {

	void submitTaskInQueue(TaskDetail taskDetail);

	int getQueueSize();

	String clearQueue();

	String removeTaskForUser(String recepientName);

	List<TaskDetail> getQueueDetails();

}
