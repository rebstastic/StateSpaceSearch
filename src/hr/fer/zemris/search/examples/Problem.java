package hr.fer.zemris.search.examples;

import java.util.Set;

import hr.fer.zemris.data.ShuttleBase;
import hr.fer.zemris.data.State;
import hr.fer.zemris.data.Teleporter;
import hr.fer.zemris.search.NeighborCostPair;

public class Problem implements IProblem {

	private State goal;

	public Problem(State goal) {
		this.goal = goal;
	}

	public boolean isGoal(State state) {
		return goal.equals(state);
	}

	public NeighborCostPair[] succ(State state) {
		NeighborCostPair[] successors;
		
		Set<State> neighbors = state.getNeighbors();
		successors = new NeighborCostPair[neighbors.size()];
		double cost;
		int i = 0;
		for(State neighbor: neighbors) {
			if(state.getClass() == Teleporter.class) {
				if(neighbor.getClass() == Teleporter.class) {
					cost = Math.abs(state.getX() - neighbor.getX()) + Math.abs(state.getY() - neighbor.getY());
				} else {
					cost = Math.abs(state.getHeight() - neighbor.getHeight());
				}
			} else if(state.getClass() == ShuttleBase.class) {
				if(((ShuttleBase) state).canLaunch()) {
					if(neighbor.getClass() == ShuttleBase.class) {
						cost = 3*(Math.abs(state.getX() - neighbor.getX()) + Math.abs(state.getY() - neighbor.getY()));
					} else {
						cost = Math.abs(state.getHeight() - neighbor.getHeight());
					} 
				} else {
					cost = Math.abs(state.getHeight() - neighbor.getHeight());
				}
			} else {
				cost = Math.abs(state.getHeight() - neighbor.getHeight());
			}
			successors[i++] = new NeighborCostPair(neighbor, cost);
		}
		
		return successors;
	}
}
