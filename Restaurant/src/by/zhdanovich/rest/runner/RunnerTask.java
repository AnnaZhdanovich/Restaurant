package by.zhdanovich.rest.runner;

import java.util.Random;

import by.zhdanovich.rest.entity.Client;
import by.zhdanovich.rest.entity.Restaurant;
import by.zhdanovich.rest.entity.Waiter;

public class RunnerTask {
	public static Random random = new Random();
	final static int CLIENTS = 10;

	public static void main(String[] args) {
		int counter = 1;
		for (int i = 0; i < CLIENTS; i++) {
			Client c = new Client(Restaurant.FALCONE, counter);
			c.start();
			counter++;
		}

		Waiter w1 = new Waiter(Restaurant.FALCONE, 1000);
		Waiter w2 = new Waiter(Restaurant.FALCONE, 2000);
		Waiter w3 = new Waiter(Restaurant.FRESH, 1000);
		Waiter w4 = new Waiter(Restaurant.FRESH, 2000);
		w1.start();
		w2.start();
		w3.start();
		w4.start();
	}
}
