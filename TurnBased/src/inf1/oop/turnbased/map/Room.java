package inf1.oop.turnbased.map;

import java.util.ArrayList;

//creates a room in the map
public class Room {	
	private int ID;
	private int startX, startY, endX, endY;
	private ArrayList<Room> connections;
	private ArrayList neighbours;
	private boolean connected;
	
	public void addConnection(Room rm){
			connections.add(rm);
	}
	
	public ArrayList<Room> getConnections(){
		return connections;
	}
	
	public void setConnected(boolean c){
		connected = c;
	}
	
	public boolean isConnected(){
		return connected;	
	}
	
	
	public Room(int ID, boolean c){
		this.ID = ID;
		this.connected = c;
		connections = new ArrayList<Room>();
	}
	
	public int getID(){
		return ID;
	}
	
	
	//accessor methods for x, y
	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

}
