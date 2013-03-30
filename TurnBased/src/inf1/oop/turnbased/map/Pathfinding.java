package inf1.oop.turnbased.map;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Pathfinding {

	// HERE COMES>>>
	public static boolean search(Map m, Vector2 startNode, Vector2 goalNode) {
		// list of visited nodes
		ArrayList<Vector2> closedList = new ArrayList<Vector2>();

		// list of nodes to visit (sorted)
		ArrayList<Vector2> openList = new ArrayList<Vector2>();
		openList.add(startNode);

		while (!openList.isEmpty()) {
			Vector2 node = openList.remove(0);
			if (node.equals(goalNode)) {
				// path found!
				return true;
			} else {
				closedList.add(node);

				// add neighbors to the open list

				Vector2[] neighbours = new Vector2[] { new Vector2(node.x+1, node.y),
						new Vector2(node.x-1, node.y), new Vector2(node.x, node.y +1), new Vector2(node.x, node.y - 1) };

				for (Vector2 neighbour : neighbours) {
					if (m.getTile((int) neighbour.x, (int) neighbour.y)
							.isPassable()) {

						if (!closedList.contains(neighbour)
								&& !openList.contains(neighbour)) {
							openList.add(neighbour);
						}
					}
				}
			}
		}

		// no path found
		return false;
	}
}

