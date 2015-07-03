package hr.fer.zemris.search.informed;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import hr.fer.zemris.data.State;
import hr.fer.zemris.data.Teleporter;
import hr.fer.zemris.search.ISearchAlgorithm;
import hr.fer.zemris.search.NeighborCostPair;
import hr.fer.zemris.search.Node;
import hr.fer.zemris.search.blind.BNode;
import hr.fer.zemris.search.examples.Problem;

public class HeuristicsH2 implements IHeuristics {

	State end;
	private String[][] map;
	
	public HeuristicsH2(State end) {
		createSimpleMap(end.getMap());
		double height = map[end.getX()][end.getY()].equals("1") ? 1.0 : 0.0;
		this.end = new State(map, end.getX(), end.getY(), height);		
	}
	
	@Override
	public double getEstimatedCost(State state) {
		double height = map[state.getX()][state.getY()].equals("1") ? 1.0 : 0.0;
		ISearchAlgorithm algorithm = new UCSH2(new Problem(new State(map, state.getX(), state.getY(), height)));
		Node node = algorithm.search(end);
		return node.getCost();	
	}
	
	private void createSimpleMap(String[][] oldMap) {
		int N = oldMap[0].length;
		map = new String[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				String letter = oldMap[i][j].substring(0, 1);
				if(letter.equals("P")|| letter.equals("S") || letter.equals("C")) {
					map[i][j] = oldMap[i][j];
				} else if(letter.equals("T")) {
					map[i][j] = "T";
				} else {
					map[i][j] = "1";
				}
			}
		}
	}
	
	private class UCSH2 implements ISearchAlgorithm {
		
		private Problem problem;
		private boolean canTeleport;
		private int openedNodes;
		
		public UCSH2(Problem problem) { 
			this.problem = problem;
			this.canTeleport = true;
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
				// Check for multiple teleporter movements.
				if (currentState.getClass() == Teleporter.class) {
					if (open.peek().getState().getClass() == Teleporter.class) {
						if (canTeleport) {
							// If current node is teleporter on Enterprise ship
							if (currentState.getY() < currentState.getMap()[0].length/2) {
								if(open.peek().getState().getY() >= open.peek().getState().getMap()[0].length/2) {
									canTeleport = false;
									continue;
								}
							}
						} else {
							open.remove();
						}
					}
				}
			}
			return null;
		}
	}
}
