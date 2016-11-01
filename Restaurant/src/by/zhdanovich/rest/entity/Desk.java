package by.zhdanovich.rest.entity;


public class Desk {
	private int deskId;	
	private int waitingTime;
	private  State statusDesk = State.FREE;	
	
	public enum State{
		BOOKED, FREE
	}

	public Desk( int deskId, int waitingTime) {	
		this.deskId=deskId;
		this.waitingTime=waitingTime;
	}
		
    public int getDeskId() {
	return deskId;
	}
		    
	public int getWaitingTime() {
	return waitingTime;
	} 
		    
	public State getStatusDesk() {
	return statusDesk;
	}

	public void setStatusDesk(State statusDesk) {
	this.statusDesk = statusDesk;
	}
}
