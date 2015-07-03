package hr.fer.zemris.data;

import java.util.HashSet;
import java.util.Set;

public class Teleporter extends State {
	
	public Teleporter(String[][] map, int x, int y, double height) {
		super(map, x, y, height);
	}

	private Set<Teleporter> getDestinations() {
		Set<Teleporter> destinations = new HashSet<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i == x && j == y) continue;
				if(map[i][j].equals(map[x][y])) {
					destinations.add(new Teleporter(map, i, j, 0));
				}
			}
		}
		return destinations;
	}

	@Override
	public Set<State> getNeighbors() {
		Set<State> neighbors = new HashSet<>();
		neighbors = super.getNeighbors();
		Set<Teleporter> destinations = getDestinations();
		for(Teleporter destination: destinations) {
			neighbors.add(destination);
		}
		return neighbors;
	}

}
