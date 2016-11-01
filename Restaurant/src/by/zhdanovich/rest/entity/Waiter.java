package by.zhdanovich.rest.entity;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.zhdanovich.rest.runner.RunnerTask;

public class Waiter extends Thread {
	private static Logger log = LogManager.getLogger(Waiter.class);
	private final int WORK_TIME = 100;	
	private Restaurant restaurant;
	private int waiterId;
	

	public Waiter(Restaurant restaurant, int waiterId) {
		this.restaurant = restaurant;
		this.waiterId = waiterId;
	}

	@Override
	public void run() {
		int counter = 0;
		while (counter < WORK_TIME) {
			restaurant.getQueue().takeOrder(this);
			try {
				TimeUnit.MILLISECONDS.sleep(RunnerTask.random.nextInt(500));
			} catch (InterruptedException e) {
				log.error("Mistake of thread ", e);
			}
			counter++;
		}
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public int getWaiterId() {
		return waiterId;
	}
}
