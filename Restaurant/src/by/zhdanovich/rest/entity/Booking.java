package by.zhdanovich.rest.entity;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.zhdanovich.rest.entity.Client.Status;
import by.zhdanovich.rest.entity.Desk.State;
import by.zhdanovich.rest.exception.WrongDataException;
import by.zhdanovich.rest.runner.RunnerTask;

public class Booking {
	private Lock lock = new ReentrantLock();
	private static Logger log = LogManager.getLogger(Booking.class);
	private Deque<Client> clients;

	public Booking() {
		clients = new LinkedList<Client>();
	}

	public void bookingDesk(Client client) throws WrongDataException {
		addClient(client);
		lock.lock();
		try {
			if (client == clients.getFirst()) {
				clients.removeFirst();
				Desk desk = this.enterToDesk(client);
				if (desk != null) {
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException e) {
						log.error("Mistake of the thread.", e);
					}

					if (RunnerTask.random.nextBoolean()) {
						System.out.println("Client" + client.getClientId() + " booked desk " + desk.getDeskId()
								+ "in the restaurant " + client.getRestaurant().getName());
						desk.setStatusDesk(State.BOOKED);
						client.setStatus(Status.SERVE);
					} else {
						desk.setStatusDesk(State.FREE);
						client.setStatus(Status.NOT_SATISFIED);
						System.out.println("Client" + client.getClientId() + " refused book desk " + desk.getDeskId()
								+ "in the restaurant " + client.getRestaurant().getName());
					}
				} else {
					switch (client.getRestaurant()) {
					case FALCONE:
						client.setRestaurant(Restaurant.FRESH);
						break;
					case FRESH:
						System.out.println("Client" + client.getClientId() + "is not satisfied. Client leaved the restaurant " + client.getRestaurant().getName());
						client.setStatus(Status.NOT_SATISFIED);
						break;
					default:
						throw new WrongDataException("Data are wrong");
					}
				}
			}
		} finally {
			lock.unlock();
		}
	}

	private Desk enterToDesk(Client client) {
		Desk result = null;
		for (Desk desk : client.getRestaurant().getDesks()) {
			if (desk.getStatusDesk() != State.BOOKED) {
				result = desk;
			}
		}
		return result;
	}

	private void addClient(Client client) {
		lock.lock();
		try {
			clients.add(client);
		} finally {
		lock.unlock();
		}
	}
		
}
