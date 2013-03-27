package ar.com.sia.algorithm.qlearning;

import java.util.Collection;

import org.apache.log4j.Logger;

public class QLearningAlgorithm {

	private Logger logger = Logger.getLogger(QLearningAlgorithm.class);
	
	private QLearningMatrixUpdater matrixUpdater;
	private int episodes, iterations;
	
	public QLearningAlgorithm(int episodes, int iterations) {
		this.episodes = episodes;
		this.iterations = iterations;
	}

	public void run(QState initialState, QState goalState, Collection<QState> states, Collection<QAction> actions, StateUpdater stateUpdater) {
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
			System.out.println(matrixUpdater.getMatrix());
		}
	}

	private QState updateCurrentState(QState current, StateUpdater stateUpdater) {
		try {
			QAction action = stateUpdater.chooseAction(current);
			QState next = current.apply(action);
			float reward = stateUpdater.getReward(current, next);
			matrixUpdater.updateValue(current, action, reward);
			logger.info("Current State: [" + current + "] <<== " + action);
			logger.info("Next State: [" + next + "]");
			logger.info("Reward: " + reward);
			logger.info("--------------------");
			return next;
		} catch (NotApplicableException e) {
			System.out.println("Action not applicable! Skipping");
			return current;
		}
	}

}
