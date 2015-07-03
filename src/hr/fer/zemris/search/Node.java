package hr.fer.zemris.search;

import hr.fer.zemris.data.State;

public class Node {

	protected Node parent;
	protected State state;
	protected double cost;
	
	public Node(Node parent, State state, double cost) {
		this.parent = parent;
		this.state = state;
		this.cost = cost;
	}

	public Node getParent() {
		return parent;
	}

	public State getState() {
		return state;
	}

	public double getCost() {
		return cost;
	}

	public static String nodePath(Node node) {
		StringBuilder sb = new StringBuilder();
		buildStringPath(sb, node);
		return sb.toString();
	}

	private static void buildStringPath(StringBuilder sb, Node node) {
		if (node.getParent() != null) {
			buildStringPath(sb, node.getParent());
			sb.append("->");
			sb.append(String.format("%n"));
		}
		sb.append(node);
	}

	@Override
	public String toString() {
		return String.format("(%d, %d)", state.getX() + 1, state.getY() + 1);
	}
	
}
