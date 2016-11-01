package by.zhdanovich.rest.entity;


public class Desk {
	private int deskId;	
	private int waitingTime;
	private  StateDesk statusDesk = StateDesk.FREE;	
	
	public enum StateDesk{
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
		    
	public StateDesk getStatusDesk() {
	return statusDesk;
	}

	public void setStatusDesk(StateDesk statusDesk) {
	this.statusDesk = statusDesk;
	}
}
