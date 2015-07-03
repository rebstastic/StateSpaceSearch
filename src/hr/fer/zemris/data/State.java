package hr.fer.zemris.data;

import java.util.HashSet;
import java.util.Set;

public class State {

	protected String[][] map;
	protected int x;
	protected int y;
	protected double height;

	protected int N;

	public State(String[][] map, int x, int y, double height) {
		this.map = map;
		this.x = x;
		this.y = y;
		this.height = height;
		N = map[0].length;

	}

	public State() {
	}

	public String[][] getMap() {
		return map;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getHeight() {
		return height;
	}

	public Set<State> getNeighbors() {
		Set<State> neighbors = new HashSet<>();

		if (x == 0) {
			if (y == 0 || y == N / 2) {
				neighbors.add(findStateType(x, y + 1));
				neighbors.add(findStateType(x + 1, y));
			} else if (y == N / 2 - 1 || y == N - 1) {
				neighbors.add(findStateType(x + 1, y));
				neighbors.add(findStateType(x, y - 1));

			} else {
				neighbors.add(findStateType(x, y + 1));
				neighbors.add(findStateType(x + 1, y));
				neighbors.add(findStateType(x, y - 1));
			}
		} else if (x == N - 1) {
			if (y == 0 || y == N / 2) {
				neighbors.add(findStateType(x, y + 1));
				neighbors.add(findStateType(x - 1, y));
			} else if (y == N / 2 - 1 || y == N - 1) {
				neighbors.add(findStateType(x, y - 1));
				neighbors.add(findStateType(x - 1, y));
			} else {
				neighbors.add(findStateType(x, y + 1));
				neighbors.add(findStateType(x, y - 1));
				neighbors.add(findStateType(x - 1, y));
			}
		} else {
			if (y == 0 || y == N / 2) {
				neighbors.add(findStateType(x, y + 1));
				neighbors.add(findStateType(x + 1, y));
				neighbors.add(findStateType(x - 1, y));
			} else if (y == N / 2 - 1 || y == N - 1) {
				neighbors.add(findStateType(x + 1, y));
				neighbors.add(findStateType(x, y - 1));
				neighbors.add(findStateType(x - 1, y));
			} else {
				neighbors.add(findStateType(x, y + 1));
				neighbors.add(findStateType(x + 1, y));
				neighbors.add(findStateType(x, y - 1));
				neighbors.add(findStateType(x - 1, y));
			}
		}

		return neighbors;

	}

	private State findStateType(int x, int y) {
		State state;
		switch (map[x][y].substring(0, 1)) {
		case "P":
			state = new State(map, x, y, 0);
			break;
		case "C":
			state = new State(map, x, y, 0);
			break;
		case "T":
			state = new Teleporter(map, x, y, 0);
			break;
		case "S":
			state = new ShuttleBase(map, x, y, 0);
			break;
		default:
			state = new State(map, x, y, Double.parseDouble(map[x][y]));
			break;
		}
		return state;
	}
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof State)) {
			return false;
		}
		State that = (State) other;

		return this.x == that.x && this.y == that.y;
	}
	
	@Override
	public int hashCode() {
		return this.x*this.y;
	}
	
}
