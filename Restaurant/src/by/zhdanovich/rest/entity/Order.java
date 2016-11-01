package by.zhdanovich.rest.entity;

public class Order {
	private int clientId;
	private StatusOrder statusOrder = StatusOrder.WAIT;

	public enum StatusOrder {
		WAIT, TAKE
	}

	public Order(int clientId) {
		this.clientId = clientId;

	}

	public int getClientId() {
		return clientId;
	}

	public void setStatusOrder(StatusOrder statusOrder) {
		this.statusOrder = statusOrder;
	}

	public StatusOrder getStatusOrder() {
		return statusOrder;
	}

}
