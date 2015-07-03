package hr.fer.zemris.search.blind;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import hr.fer.zemris.data.State;
import hr.fer.zemris.search.Node;
import hr.fer.zemris.search.ISearchAlgorithm;
import hr.fer.zemris.search.NeighborCostPair;
import hr.fer.zemris.search.examples.IProblem;

public class UniformCostSearch implements ISearchAlgorithm {

	private IProblem problem;
	private int openedNodes;
	
	public UniformCostSearch(IProblem problem) {
		this.problem = problem;
	}
	
	@Override
	public int getOpenedNodes() {
		return openedNodes;
	}

	@Override
	public Node search(State initialState) {
		Queue<BNode> open = new PriorityQueue<>();
		open.add(new BNode(null, initialState, 0.0));
		Set<State> visited = new HashSet<>();
		while(!open.isEmpty()) {
			BNode currentNode = open.remove();
			State currentState = currentNode.getState();
			if(problem.isGoal(currentState)){
				openedNodes = open.size();
				return currentNode;
			}
			visited.add(currentState);
			NeighborCostPair[] neighbors = problem.succ(currentState);
			for(NeighborCostPair next: neighbors) {
				if(visited.contains(next.getState())) continue;
				open.add(new BNode(currentNode, next.getState(), currentNode.getCost() + next.getCost()));
			}
		}
		return null;
	}
}
