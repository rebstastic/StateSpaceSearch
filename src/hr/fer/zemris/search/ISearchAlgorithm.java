package hr.fer.zemris.search;

import hr.fer.zemris.data.State;

public interface ISearchAlgorithm {

	Node search(State state);

	int getOpenedNodes();
}
