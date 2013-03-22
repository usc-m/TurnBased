package inf1.oop.turnbased.map;

import java.util.ArrayList;
import java.util.Random;

//this class can be in the map renderer or the map screen or the map object alternatively
/*TODO Divide map: DONE + TESTED
 *TODO Flag grid: DONE
 *TODO Pick random start: DONE
 *TODO Connect to random unconnected neighbour while there is one: DONE 
 *TODO Connect to random connected neighbour of an unconnected room, if there is one: DONE
 *TODO Test full connection: DONE!
 *TODO (Optional) Random Connection 
 *TODO Draw rooms on map - varied size later on(ranging from min-max values): DONE
 *TODO Draw Corridors: DONE
 *TODO Draw doors: Nah.
 *FIXME There is a bug where some rooms have no corridors between, although debugging shows them connected..
 */
public class MapGenerator {
	
	private Map map;
	private ArrayList<Room> rooms;
	
	private int roomBase;
	private int startRoom, endRoom, mapStartX, mapStartY, mapEndX, mapEndY;
	
	//14,28,42; 8,16,24
	//divides a map into a grid, 3x3 in our case but configurble from parameters
	public void divMap(int times){
		roomBase = times;
		rooms = new ArrayList<Room>();
		for(int ID = 0; ID < times*times; ID++){
			Room temp = new Room(ID, false);
			
			System.out.println("ROOM" + ID);
			
			//set start X by division(resetting due to mod)
			temp.setStartX((ID%times)*((map.getWidth())/times));
			temp.setEndX(temp.getStartX()+map.getWidth()/times - 1);
			System.out.println("X: " + temp.getStartX());
			System.out.println("X: " + temp.getEndX());
			
			//set start y by division(resetting due to ceil)
			temp.setStartY((int) ((Math.ceil(ID/times))*(map.getHeight())/times));			
			temp.setEndY(temp.getStartY()+map.getHeight()/times - 1);
			System.out.println("Y: " + temp.getStartY());
			System.out.println("Y: " + temp.getEndY());
			System.out.println();
			
			rooms.add(temp);
		}
	}
	
	public void connect(){
		//STEP 1 -  CONNECT ALL UNCONNECTED NEIGHBOURS------------------------------------------------
		//picks random room based on index from 0 to times*times(nb. of rooms)
		Random rand = new Random();
		int currentRoom = rand.nextInt(roomBase*roomBase);
		
		//the starting room for the player
		setStartRoom(currentRoom);
		
	    rooms.get(currentRoom).setConnected(true);
		boolean found = false;
		int index = 0;
		
		boolean allC = checkConnected(rooms);
		
		//find and check the room's neighbours
		ArrayList<Room> neighbours = findNs(currentRoom); //could just store ID less memory - search needed, lazy and not much difference
		boolean nC = checkConnected(neighbours);
		
		//while there are unconnected neighbours
		while(!nC){
			
			System.out.println("Current room: " + currentRoom);
			index = 0;
			
			//while no unconnected neighbour at index
			while(found == false){
				//pick random neighbour
				index = rand.nextInt(neighbours.size());
				if(!neighbours.get(index).isConnected()){
					System.out.println("Neighbor to connect: " + neighbours.get(index).getID());
					found = true;
				}
			}
			
		    System.out.println("\n" + nC);
			rooms.get(currentRoom).addConnection(neighbours.get(index)); //connect the random unconnected neighbor to this room
			
			currentRoom = neighbours.get(index).getID();
			rooms.get(currentRoom).setConnected(true);
			found = false;
			neighbours = findNs(currentRoom);
			nC = checkConnected(neighbours);
		}
		System.out.println("\n" + nC + " - all unconnected neighbors connected");
		
		//STEP 1 FINISHED--------------------------------------------------------------------------------

		//STEP 2 CONNECT ALL UNCONNECTED ROOMS TO A RANDOM CONNECTED NEIGHBOUR---------------------------
		//reset for second search
		found = false;
		index = 0;
		allC = checkConnected(rooms);
				
		while(!allC){
			//find a random unconnected room
			do{
				//System.out.println("Checking for unconnected room");
				currentRoom = rand.nextInt(rooms.size());
			}while(rooms.get(currentRoom).isConnected() == true || hasConnectedNs(findNs(currentRoom)) == false);
					
			//and find its neighbours
			neighbours = findNs(currentRoom);
				
			System.out.println("Current room: " + currentRoom);
					
			index = 0;
			
			//FIXME Perhaps is possible for no neighbours to be connected - method that checks this and keeps cycling to others if false
			//FIXED! added check for connected neighbours, else it got stuck on trying to find a connected neighbour when there was none, very rare but annoying as hell
			//keep searching for a connected neighbour
			while(found == false){
				index = rand.nextInt(neighbours.size());
				//System.out.println("We are stuck");
				System.out.println(index);
				if(neighbours.get(index).isConnected()){
					System.out.println("Neighbor to connect: " + neighbours.get(index).getID());
					found = true;
				}
			}
					
				    
			rooms.get(currentRoom).addConnection(rooms.get(index)); //connect the random connected neighbor to this room
			rooms.get(currentRoom).setConnected(true);
					
			found = false;
					
			allC = checkConnected(rooms);
			System.out.println("\n" + allC);
		}
		endRoom = currentRoom;		
		System.out.println("\n" + allC + " - all rooms connected");
	}
	//STEP 2 DONE-------------------------------------------------------------------------------------------
	
	//SEARCH FUNCTIONS--------------------------------------------------------------------------------------
	//finds all the neighbours of the current room
	public ArrayList<Room> findNs(int ID){ 
		ArrayList<Room> neighbours = new ArrayList<Room>();
		Room cur = rooms.get(ID);		
		
		//loop through all rooms, way better loop than simple for ones(no crazy index juggling)
		for(Room rm : rooms){
			Room temp = rm;  //for my peace of mind mainly -F
			if(temp.getStartX() == cur.getStartX() + (map.getWidth()/roomBase) && (temp.getStartY() == cur.getStartY())){   //constraint to kep neighbors to 4(no diagonal)
				neighbours.add(temp);
			}
			
			if(temp.getStartX() == cur.getStartX() - (map.getWidth()/roomBase) && (temp.getStartY() == cur.getStartY())){   //constraint to kep neighbors to 4
				neighbours.add(temp);
			}
			
			else if(temp.getStartX() == cur.getStartX() && (temp.getStartY() == cur.getStartY() + (map.getHeight()/roomBase))){//needs change to detect y neighbors
				neighbours.add(temp);
			}
			
			else if(temp.getStartX() == cur.getStartX() && (temp.getStartY() == cur.getStartY() - (map.getHeight()/roomBase))){
				neighbours.add(temp);
			}
		}
		
		for(Room nb : neighbours){
				System.out.println("Neighbor: " + nb.getID());
		}
		
		return neighbours;
	}
	

	//checks if all elements in the list are connected
	public boolean checkConnected(ArrayList<Room> rms){
		boolean connected = true;
		for(Room rm : rms){
			if(!rm.isConnected()){
				connected = false;
			}
		}	
		return connected;
	}
	
	//essential function to prevent a bug
	public boolean hasConnectedNs(ArrayList<Room> ns){
		boolean nConnected = false;
		for(Room nb : ns){
			if(nb.isConnected()){
				nConnected = true;
			}
		}	
		return nConnected;
	}
	
	//SEARCH FUNCTIONS END-----------------------------------------------------------------------------------
	
	//MAP GENERATION FROM DATA-------------------------------------------------------------------------------
	public void placeMap(){
		//fill with walls
		Tile temp = new Tile("assets/data/spr_16x16Wall.png");
		temp.setPassable(false);
		for (int x=0; x<map.getWidth(); x++)
		{
			for (int y=0; y<map.getHeight(); y++)
			{	
				map.setTile(x, y,temp);
			}
		}
		
		//fill with empty(for now pre-determined 10x5) space, will be some semi-random size
		temp = new Tile("assets/data/spr_16x16Floor.png");
		temp.setPassable(true);
		for(Room rm : rooms){
			for (int xp=1;xp<10;xp++) {
				for (int yp=1;yp<5;yp++){
					map.setTile(rm.getStartX() + xp, rm.getStartY() + yp, temp);
					
					//set stairs tile
					if(rm.getStartX() + xp == mapEndX && rm.getStartY() + yp == mapEndY){
						map.setTile(rm.getStartX() + xp, rm.getStartY() + yp, new Tile("assets/data/spr_16x16Stairs.png"));
						map.getTile(rm.getStartX() + xp, rm.getStartY() + yp).setPassable(true);
					}
				}
			}
		}
	}
	
	//SET ENTRY/EXIT POINTS - FIRST AND LAST ROOMS WHEN GENERATING-----------
	public void setPortals(){
		setMapStartX();
		setMapStartY();
		setMapEndX();
		setMapEndY();
		System.out.println("PORTALS PLACED: " + mapStartX + " " + mapStartY);
	}
	
	public void setMapStartX(){
		Room start = rooms.get(startRoom);
		mapStartX = (start.getStartX() + start.getEndX()) / 2;
	}
	
	public void setMapStartY(){
		Room start = rooms.get(startRoom);
		mapStartY = (start.getStartY() + start.getEndY()) / 2;
	}
	
	public void setMapEndX(){
		Room end = rooms.get(endRoom);
		mapEndX = (end.getStartX() + end.getEndX()) / 2;
	}
	
	public void setMapEndY(){
		Room end = rooms.get(endRoom);
		mapEndY = (end.getStartY() + end.getEndY()) / 2;
	}
	//END PORTAL PLACEMENT------------------------------------------------------
	
	//PLACE CORRIDORS CONNECTING ROOMS(size of 1x1 for now)---------------------------------------
	public void setCorridors(){
		ArrayList<Room> connections = new ArrayList<Room>();
		int corStartX, corEndX, corLength, corStartY, corEndY, temp;
		
		Tile square = new Tile("assets/data/spr_16x16Floor.png");
		square.setPassable(true);
		
		//for each room connect
		for(Room rm : rooms){
			connections = rm.getConnections();
			for(Room con : connections){
				//get x,y for the two rooms
				corStartX = (rm.getStartX() + rm.getEndX())/2;
				corEndX = (con.getStartX() + con.getEndX())/2;
				
				//since our coords are left to right, we change start-end, so that we do not bother filling right to left
				if(corStartX > corEndX){
					temp = corStartX;
					corStartX = corEndX;
					corEndX = temp;
				}
				
				corStartY = (rm.getStartY() + rm.getEndY())/2;
				corEndY = (con.getStartY() + con.getEndY())/2;
				
				//again, fixing for our coordinate system
				if(corStartY > corEndY){
					temp = corStartY;
					corStartY = corEndY;
					corEndY = temp;
				}
				
				//also checks not to overwrite stairs tile
				//different x means neighbour is vertical(no diagonals)
				if(corStartX != corEndX){
					corLength = Math.abs(corStartX - corEndX);
					for (int xp=0; xp<corLength; xp++) {
						if(corStartX + xp != mapEndX || corStartY != mapEndY)
							map.setTile(corStartX + xp, corStartY, square);
					}
					for (int xp=0; xp<corLength; xp++) {
						if(corStartX + xp != mapEndX || corStartY -1 != mapEndY)
							map.setTile(corStartX + xp, corStartY-1, square);
					}
				}
					
				//different y means neighbour is horizontal(no diagonals)
				else{
					corLength = Math.abs(corStartY - corEndY);
					for (int yp=0;yp<corLength;yp++){
						if(corStartX != mapEndX || corStartY + yp != mapEndY)
							map.setTile(corStartX, corStartY + yp, square);						
					}
					for (int yp=0;yp<corLength;yp++){
						if(corStartX - 1 != mapEndX || corStartY + yp != mapEndY)
							map.setTile(corStartX - 1, corStartY + yp, square);						
					}		
				}			
			}
		}
	}
	
	
	
	public MapGenerator(Map m){
		map = m;
	}
	
	public Map generate(){
		divMap(3);
		connect();
		setPortals();
		placeMap();
		setCorridors();
		return map;
	}
	
	public static void main(String[] args){
		MapGenerator mp = new MapGenerator(new Map(42,24,16,16));
		mp.generate();
	}

	public int getStartRoom() {
		return startRoom;
	}

	public void setStartRoom(int startRoom) {
		this.startRoom = startRoom;
	}

	public int getEndRoom() {
		return endRoom;
	}

	public void setEndRoom(int endRoom) {
		this.endRoom = endRoom;
	}

	public int getMapStartX() {
		return mapStartX;
	}

	public int getMapStartY() {
		return mapStartY;
	}

	public int getMapEndX() {
		return mapEndX;
	}

	public int getMapEndY() {
		return mapEndY;
	}
}
