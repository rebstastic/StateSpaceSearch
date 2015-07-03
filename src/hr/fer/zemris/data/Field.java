package hr.fer.zemris.data;

import java.util.Set;

public class Field extends State {

	public Field(String[][] map, int x, int y, int height) {
		super(map, x, y, height);
	}

	@Override
	public Set<State> getNeighbors() {
		return super.getNeighbors();
	}

}
