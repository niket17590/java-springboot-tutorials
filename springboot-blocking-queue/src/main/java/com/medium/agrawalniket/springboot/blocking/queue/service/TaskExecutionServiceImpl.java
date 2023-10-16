/**
 * 
 */
package com.medium.agrawalniket.springboot.blocking.queue.service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.medium.agrawalniket.springboot.blocking.queue.dto.TaskDetail;

/**
 * @author Niket Agrawal
 *
 */
@Service
public class TaskExecutionServiceImpl implements TaskExecutionService {

	static final Logger logger = LoggerFactory.getLogger(TaskExecutionServiceImpl.class);

	private BlockingQueue<TaskDetail> blockingQueue;

	public TaskExecutionServiceImpl() {
		blockingQueue = new LinkedBlockingDeque<>();
		initiateThread();
	}

	/**
	 * Single Thread on Which Tasks will be performed
	 */
	private void initiateThread() {
		Thread thread = new Thread(() -> {
			while (true) {
				try {
					if (!blockingQueue.isEmpty()) {
						logger.info("Processing Next Task from Queue");
						TaskDetail taskDetail = blockingQueue.take();
						boolean result = processTask(taskDetail);
						if (result)
							logger.info("Task performed successfully");
						else
							logger.error("Task compelted with errors");
						// Now we can either email or publish push notification
					}
				} catch (InterruptedException e) {
					logger.error("There was an error while processing ", e);
					Thread.currentThread().interrupt();
				}
			}
		});
		thread.setName("MyCustomThread");
		thread.start();
		logger.info("Worker Thread {} initiated successfully", thread.getName());
	}

	/**
	 * Method to Submit Tasks in Queue
	 */
	@Override
	public void submitTaskInQueue(TaskDetail taskDetail) {
		blockingQueue.add(taskDetail);
		logger.info("Task for Customer : {} submitted in Queue", taskDetail.getRecepientName());
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

	/**
	 * Method to get Queue Size
	 */
	@Override
	public int getQueueSize() {
		return blockingQueue.size();
	}

	/**
	 * Method to clear all tasks from queue
	 */
	@Override
	public String clearQueue() {
		int size = getQueueSize();
		blockingQueue.clear();
		return "Cleared Queue. It had total tasks : " + size;
	}

	/**
	 * Method to remove a Task from Queue
	 */
	@Override
	public String removeTaskForUser(String recepientName) {
		List<TaskDetail> taskList = blockingQueue.stream().filter(task -> task.getRecepientName().equals(recepientName))
				.toList();
		blockingQueue.removeAll(taskList);
		return "Total " + taskList.size() + " removed from Queue.";
	}

	/**
	 * Method to get all Task details present in Queue
	 */
	@Override
	public List<TaskDetail> getQueueDetails() {
		return blockingQueue.stream().toList();
	}
}
