package hr.fer.zemris.search.informed;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import hr.fer.zemris.data.State;
import hr.fer.zemris.search.ISearchAlgorithm;
import hr.fer.zemris.search.NeighborCostPair;
import hr.fer.zemris.search.Node;
import hr.fer.zemris.search.examples.Problem;

public class AStarSearch implements ISearchAlgorithm {

	private Problem problem;
	
	private IHeuristics heuristics;

	private int openedNodes;
	
	public AStarSearch(Problem problem, IHeuristics heuristics) {
		this.problem = problem;
		this.heuristics = heuristics;
	}

	@Override
	public int getOpenedNodes() {
		return openedNodes;
	}

	@Override
	public Node search(State initialState) {
		Queue<HNode> open = new PriorityQueue<HNode>(HNode.COMPARE_BY_TOTAL_ESTIMATE);
		Map<State,HNode> openMap = new HashMap<>();
		Map<State,HNode> closedMap = new HashMap<>();

		HNode initialNode = new HNode(null, initialState, 0.0, heuristics.getEstimatedCost(initialState));
		open.add(initialNode);
		openMap.put(initialNode.getState(), initialNode);
		
		while(!open.isEmpty()) {
			HNode node = open.remove();
			openMap.remove(node.getState());
			
			if(problem.isGoal(node.getState())) {
				openedNodes = open.size();
				return node;
			}
			closedMap.put(node.getState(), node);
			
			for(NeighborCostPair next : problem.succ(node.getState())) {
				HNode nextNode = new HNode(
					node, 
					next.getState(), 
					node.getCost() + next.getCost(), 
					node.getCost() + next.getCost() + heuristics.getEstimatedCost(next.getState()));

				// Ako u listi otvorenih imamo cvor za isto stanje ali jeftiniji, vozi dalje...
				HNode nodeInOpen = openMap.get(nextNode.getState());
				if(nodeInOpen!=null && nodeInOpen.getCost() <= nextNode.getCost()) {
					continue;
				}
				
				// Ako u listi zatvorenih imamo cvor za isto stanje ali jeftiniji, vozi dalje...
				HNode nodeInClosed = closedMap.get(nextNode.getState());
				if(nodeInClosed!=null && nodeInClosed.getCost() <= nextNode.getCost()) {
					continue;
				}
				
				if(nodeInOpen!=null) {
					openMap.remove(nodeInOpen.getState());
					open.remove(nodeInOpen);
				}
				if(nodeInClosed!=null) {
					closedMap.remove(nodeInClosed.getState());
				}
				
				open.add(nextNode);
				openMap.put(nextNode.getState(), nextNode);
			}
		}
		return null;
	}

	
}
