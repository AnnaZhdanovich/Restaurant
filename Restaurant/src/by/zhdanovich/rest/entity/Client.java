package by.zhdanovich.rest.entity;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.zhdanovich.rest.entity.Desk.StateDesk;
import by.zhdanovich.rest.entity.Order.StatusOrder;
import by.zhdanovich.rest.exception.WrongDataException;

public class Client extends Thread {
	private static Logger log = LogManager.getLogger(Client.class);
	private Restaurant restaurant;
	private int clientId;
	private Desk desk;
	
	private Status status = Status.WAITING;
	public enum Status {
		WAITING, SERVE, NOT_SATISFIED
	}

	public Client(Restaurant restaurant, int clientId) {
		this.restaurant = restaurant;
		this.clientId = clientId;
	}

	@Override
	public void run() {

		try {
			while ((status != Status.SERVE) && (status != Status.NOT_SATISFIED)) {
				System.out
						.println("Client " + clientId + " wants to book a desk  in restaurant " + restaurant.getName());
				restaurant.getBooking().bookingDesk(this);
			}

			if (status == Status.SERVE) {
				Order order = createOrder(this);
				try {
					TimeUnit.MILLISECONDS.sleep(300);
				} catch (InterruptedException e) {
					log.error("Mistake of thread. ", e);
				}

				if (order.getStatusOrder() != StatusOrder.TAKE) {
					System.out.println("Client " + clientId + " does not wait for the order and went home from the restaurant " + restaurant.getName());
					desk.setStatusDesk(StateDesk.FREE);
				} else {
					System.out.println("Client " + clientId + "  was satisfied and went home from  " + restaurant.getName());
				}
			}
		} catch (WrongDataException e) {
			log.error("Mistake in data", e);
		}
	}

	private Order createOrder(Client client) throws WrongDataException {
		Order order = new Order(client.getClientId());
		switch (client.getRestaurant()) {
		case FALCONE:
			client.getRestaurant().getQueue().makeOrder(order);
			break;
		case FRESH:
			client.getRestaurant().getQueue().makeOrder(order);
			break;
		default:
			throw new WrongDataException("Data are wrong");
		}		
		return order;
	}

	public int getClientId() {
		return clientId;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	public Desk getDesk() {
		return desk;
	}

	public void setDesk(Desk desk) {
		this.desk = desk;
	}


}
