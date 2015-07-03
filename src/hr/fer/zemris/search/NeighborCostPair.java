package hr.fer.zemris.search;

import hr.fer.zemris.data.State;

public class NeighborCostPair {

	State state;
	double cost;

	public NeighborCostPair(State state, double cost) {
		this.state = state;
		this.cost = cost;
	}

	public State getState() {
		return state;
	}

	public double getCost() {
		return cost;
	}

	@Override
	public String toString() {
		return String.format("(%d, %d, %f)", state.getX() + 1, state.getY() + 1, cost);
	}

}
