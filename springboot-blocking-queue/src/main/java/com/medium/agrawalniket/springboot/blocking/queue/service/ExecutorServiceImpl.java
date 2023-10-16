package com.medium.agrawalniket.springboot.blocking.queue.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.medium.agrawalniket.springboot.blocking.queue.dto.TaskDetail;

@Service
public class ExecutorServiceImpl {

	static final Logger logger = LoggerFactory.getLogger(ExecutorServiceImpl.class);

	ExecutorService executorService;

	public ExecutorServiceImpl() {
		executorService = Executors.newSingleThreadExecutor();
	}

	public void submitTask(TaskDetail taskDetail) {
		executorService.submit(() -> processTask(taskDetail));
		logger.info("Task for Customer : {} submitted to ExecutorService", taskDetail.getRecepientName());
	}

	private boolean processTask(TaskDetail taskDetail) {
		logger.info("Running on Thread {}", Thread.currentThread().getName());
		boolean answer = false;
		switch (taskDetail.getOperationName()) {
		case EMAIL:
			answer = sendEmail(taskDetail.getRecepientName());
			break;
		case PRINT:
			answer = performPrinting(taskDetail.getRecepientName());
			break;
		default:
			logger.error("Operation Name is not known");
			break;
		}
		return answer;
	}

	private boolean performPrinting(String recepientName) {
		logger.info("Letter will be printed for User : {}", recepientName);
		// Perform Printing Command & Respond true/false accordingly
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			logger.error("Exception In Sleep", e);
			Thread.currentThread().interrupt();
		}
		logger.info("Printing Done");
		return true;
	}

	private boolean sendEmail(String recepientName) {
		logger.info("Email will be triggered to User : {}", recepientName);
		// Trigger email & Respond true/false accordingly
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			logger.error("Exception In Sleep", e);
			Thread.currentThread().interrupt();
		}
		logger.info("Email triggered");
		return true;
	}


}
