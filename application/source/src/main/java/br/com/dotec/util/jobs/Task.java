package br.com.dotec.util.jobs;

import org.springframework.scheduling.TaskScheduler;

public interface Task extends Runnable{
	void schedule(TaskScheduler scheduler);

}
