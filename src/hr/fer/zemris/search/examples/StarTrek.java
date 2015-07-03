package hr.fer.zemris.search.examples;

import hr.fer.zemris.data.Field;
import hr.fer.zemris.data.State;
import hr.fer.zemris.search.ISearchAlgorithm;
import hr.fer.zemris.search.Node;
import hr.fer.zemris.search.blind.UniformCostSearch;
import hr.fer.zemris.search.informed.AStarSearch;
import hr.fer.zemris.search.informed.HeuristicsH3;
import hr.fer.zemris.search.informed.HeuristicsH4;
import hr.fer.zemris.search.informed.IHeuristics;
import hr.fer.zemris.search.informed.HeuristicsH1;
import hr.fer.zemris.search.informed.HeuristicsH2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StarTrek {

	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.println("Invalid number of arguments.");
			System.exit(1);
		}

		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(args[0]));
		} catch (IOException e) {
			System.err.println("Can not open a file: " + args[0]);
			System.exit(1);
		}

		int N = lines.size();
		if (N % 2 != 0) {
			System.err
					.println("Invalid input. Number of rows and colons must be even.");
			System.exit(1);
		}
		String[][] map = new String[N][N];

		int row = 0;
		for (String line : lines) {
			String[] splitedLine = line.split("[\\s+]");
			if (splitedLine.length != N) {
				System.err
						.println("Invalid input. Number of rows and colons must be equal.");
				System.exit(1);
			}
			map[row++] = splitedLine;
		}
		
		State start = null;
		State end = null;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j].equals("P")) {
					start = new Field(map, i, j, 0);
				}
				if(map[i][j].equals("C")) {
					end = new Field(map, i, j, 0);
				}
			}
		}
		
		Problem problem = new Problem(end);
		IHeuristics h1 = new HeuristicsH1(end);
		IHeuristics h2 = new HeuristicsH2(end);
		IHeuristics h3 = new HeuristicsH3(end);
		IHeuristics h4 = new HeuristicsH4(end);
		ISearchAlgorithm algorithm = null;
		
		int num = 3;
		switch (num) {
		case 0:
			algorithm = new UniformCostSearch(problem);
			break;
		case 1:
			algorithm = new AStarSearch(problem, h1);
			break;
		case 2:
			algorithm = new AStarSearch(problem, h2);
			break;
		case 3:
			algorithm = new AStarSearch(problem, h3);
			break;
		case 4:
			algorithm = new AStarSearch(problem, h4);
			break;
		default:
			System.err.println("Algorithm doesn't exists: " + num);
			System.exit(1);
		}
		
		Node last = algorithm.search(start);
		System.out.println("Minimal cost: " + last.getCost());
		System.out.println("Opened nodes: " + algorithm.getOpenedNodes());
		System.out.println("Path:");
		System.out.println(Node.nodePath(last));

	}

}
