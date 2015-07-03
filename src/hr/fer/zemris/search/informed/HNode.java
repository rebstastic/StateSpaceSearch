package hr.fer.zemris.search.informed;

import java.util.Comparator;

import hr.fer.zemris.data.State;
import hr.fer.zemris.search.Node;

public class HNode extends Node {

	double estimatedTotalCost;

	public HNode(Node parent, State state, double cost,
			double estimatedTotalCost) {
		super(parent, state, cost);
		this.estimatedTotalCost = estimatedTotalCost;
	}

	public double getEstimatedTotalCost() {
		return estimatedTotalCost;
	}

	public final static Comparator<HNode> COMPARE_BY_HEURISTICS = (o1, o2) -> Double
			.compare(o1.estimatedTotalCost-o1.cost, o2.estimatedTotalCost-o2.cost);

	public final static Comparator<HNode> COMPARE_BY_TOTAL_ESTIMATE = (o1, o2) -> Double
			.compare(o1.estimatedTotalCost, o2.estimatedTotalCost);

}
