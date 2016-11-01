package by.zhdanovich.rest.entity;

import java.util.ArrayList;
import java.util.List;


public enum Restaurant {
	
	FALCONE("Falcone", new ArrayList<Desk>(), new OrderQueue(), new Booking()){
		{	getDesks().add(new Desk(1,200));
			getDesks().add(new Desk(2,300));
			getDesks().add(new Desk(3,250));
			getDesks().add(new Desk(4,100));
			getDesks().add(new Desk(5,100));			
		}
	}, 
	FRESH("Fresh", new ArrayList<Desk>(),  new OrderQueue(),  new Booking()){
		{
			getDesks().add(new Desk(1,400));
			getDesks().add(new Desk(2,500));
			getDesks().add(new Desk(3,250));
			getDesks().add(new Desk(4,300));
			getDesks().add(new Desk(5,100));
		}
	}; 
	

private String name;
private List<Desk> desks;
private OrderQueue queue;
private Booking booking;

private Restaurant(String name, List<Desk> desks, OrderQueue queue, Booking booking) {
	this.name=name;
	this.desks=desks;
	this.queue=queue;
	this.booking=booking;
}

public List<Desk> getDesks() {
return desks;
}

public String getName() {
	return name;
}

public OrderQueue getQueue() {
	return queue;
}
public Booking getBooking() {
	return booking;
}

}
