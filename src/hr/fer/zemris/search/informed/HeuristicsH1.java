package hr.fer.zemris.search.informed;

import hr.fer.zemris.data.State;

public class HeuristicsH1  implements IHeuristics {

	State end;
	
	public HeuristicsH1(State end) {
		this.end = end;
	}
	
	@Override
	public double getEstimatedCost(State state) {
		return Math.abs(state.getX() - end.getX()) + Math.abs(state.getY()-end.getY());
	}
	
}
