package hr.fer.zemris.search.informed;

import hr.fer.zemris.data.State;

public class HeuristicsH3 implements IHeuristics {

	State end;
	
	public  HeuristicsH3(State end) {
		this.end = end;
	}
	
	@Override
	public double getEstimatedCost(State state) {
		double x = state.getX() - end.getX();
		double y = state.getY() - end.getY();
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

}
