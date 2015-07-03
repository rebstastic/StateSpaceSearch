package hr.fer.zemris.data;

import java.util.HashSet;
import java.util.Set;

public class ShuttleBase extends State {
	
	public ShuttleBase(String[][] map, int x, int y, double height) {
		super(map, x, y, height);
	}

	public boolean canLaunch() {
		if (map[x][y].equals("SS")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Set<State> getNeighbors() {
		Set<State> neighbors = new HashSet<>();
		neighbors = super.getNeighbors();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(canLaunch()) {
					if(map[i][j].equals("SL")) {
						neighbors.add(new ShuttleBase(map, i, j, 0));
					}
				}				
			}
		}
		return neighbors;
	}	
	
}
