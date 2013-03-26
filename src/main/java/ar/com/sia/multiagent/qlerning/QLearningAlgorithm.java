package ar.com.sia.multiagent.qlerning;

import java.util.Collection;

public abstract class QLearningAlgorithm {

	private QLearningMatrixUpdater matrixUpdater;
	private int episodes, iterations;

	public QLearningAlgorithm(int episodes, int iterations) {
		this.episodes = episodes;
		this.iterations = iterations;
	}

	public void run(QState initialState, QState goalState, Collection<QState> states, Collection<QAction> actions) {
		float alpha = 0.1f;
		float gamma = 0.9f;
		matrixUpdater = new QLearningMatrixUpdater(alpha, gamma, new QMatrix(states, actions));
		QState currentState;
		for (int epidode = 0; epidode < episodes; epidode++) {
			currentState = initialState;
			for (int iteration = 0; iteration < iterations; iteration++) {
				currentState = updateCurrentState(currentState, chooseAction(currentState));
				if (goalState.equals(currentState)) {
					break;
				}
			}
		}
	}

	private QState updateCurrentState(QState current, QAction action) {
		QState next = next(current, action);
		matrixUpdater.updateValue(current, action, getReward(current, next));
		return next;
	}
	
	public abstract QAction chooseAction(QState state);
	
	public abstract float getReward(QState state, QState next);

	public abstract QState next(QState state, QAction action);

}
