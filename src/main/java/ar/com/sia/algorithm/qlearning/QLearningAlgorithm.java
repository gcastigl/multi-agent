package ar.com.sia.algorithm.qlearning;

import java.util.Collection;

public class QLearningAlgorithm {

	private QLearningMatrixUpdater matrixUpdater;
	private int episodes, iterations;
	private Trainable trainee;
	
	public QLearningAlgorithm(Trainable trainee, int episodes, int iterations, QState initialState, QState goalState, Collection<QState> states, Collection<QAction> actions, StateUpdater stateUpdater) {
		this.episodes = episodes;
		this.iterations = iterations;
	}

	public void run() {
		float alpha = 0.1f;
		float gamma = 0.9f;
		matrixUpdater = new QLearningMatrixUpdater(alpha, gamma, new QMatrix(states, actions));
		QState currentState;
		for (int epidode = 0; epidode < episodes; epidode++) {
			currentState = initialState;
			for (int iteration = 0; iteration < iterations; iteration++) {
				currentState = updateCurrentState(currentState, stateUpdater);
				if (goalState.equals(currentState)) {
					break;
				}
			}
		}
	}

	private QState updateCurrentState(QState current, StateUpdater stateUpdater) {
		QAction action = stateUpdater.chooseAction(current);
		QState next = current.apply(action);
		matrixUpdater.updateValue(current, action, stateUpdater.getReward(current, next));
		return next;
	}

}
