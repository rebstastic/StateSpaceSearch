package hr.fer.zemris.search.informed;

import hr.fer.zemris.data.State;

public class HeuristicsH4 implements IHeuristics {

	private State end;
	
	public HeuristicsH4(State end) {
		this.end = end;
	}

	@Override
	public double getEstimatedCost(State state) {
		IHeuristics h1 = new HeuristicsH1(end);
		IHeuristics h2 = new HeuristicsH3(end);
		return h1.getEstimatedCost(state) + h2.getEstimatedCost(state);
	}

	
	
}
