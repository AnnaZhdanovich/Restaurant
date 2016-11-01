package by.zhdanovich.rest.entity;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import by.zhdanovich.rest.entity.Order.StatusOrder;

public class OrderQueue {
	private Lock lock = new ReentrantLock();
	private Deque<Order> list;

	public OrderQueue() {
		this.list = new LinkedList<Order>();
	}

	public void makeOrder(Order obj) {
		lock.lock();
		try {
			list.addLast(obj);
			System.out.println("Client "+ obj.getClientId() + "  made order!!!!");
		} finally {
			lock.unlock();
		}
	}

	public void cancelOrder(Order obj) {
		list.remove(obj);
	}

	public Order takeOrder(Waiter w) {
		lock.lock();
		Order result = null;
		try {
			if (!list.isEmpty()) {
				result = list.getFirst();
				result.setStatusOrder(StatusOrder.TAKE);
				System.out.println("Waiter " + w.getWaiterId() + " took order of  " + result.getClientId()
						+ " in rastourant " + w.getRestaurant());
				list.removeFirst();
			}
			return result;
		} finally {
			lock.unlock();
		}

	}

}