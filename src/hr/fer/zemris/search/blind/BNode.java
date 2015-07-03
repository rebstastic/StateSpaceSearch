package hr.fer.zemris.search.blind;

import hr.fer.zemris.data.State;
import hr.fer.zemris.search.Node;

public class BNode extends Node implements Comparable<BNode> {

	public BNode(BNode parent, State state, double cost) {
		super(parent, state, cost);
	}

	@Override
	public int compareTo(BNode other) {
		return Double.compare(this.cost, other.cost);
	}

}
