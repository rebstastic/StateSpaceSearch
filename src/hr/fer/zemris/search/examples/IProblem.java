package hr.fer.zemris.search.examples;

import hr.fer.zemris.data.State;
import hr.fer.zemris.search.NeighborCostPair;

public interface IProblem {
	
	public boolean isGoal(State state);
	
	public NeighborCostPair[] succ(State state);
	
}
