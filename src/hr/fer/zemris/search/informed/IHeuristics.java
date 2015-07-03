package hr.fer.zemris.search.informed;

import hr.fer.zemris.data.State;

public interface IHeuristics {

	double getEstimatedCost(State state);
	
}
