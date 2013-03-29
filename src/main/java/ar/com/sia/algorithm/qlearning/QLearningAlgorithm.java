package ar.com.sia.algorithm.qlearning;

import java.util.Collection;

import org.apache.log4j.Logger;

public abstract class QLearningAlgorithm {

	private Logger logger = Logger.getLogger(QLearningAlgorithm.class);

	private QLearningMatrixUpdater matrixUpdater;
	protected float alpha = 0.1f;
	protected float gamma = 0.9f;
	protected float epsilon = 0.8f;

	public void run(Collection<QState> states, Collection<QAction> actions, int episodes, int iterations) {
		debugInitialParameters(alpha, gamma, episodes, iterations);
		matrixUpdater = new QLearningMatrixUpdater(alpha, gamma, new QMatrix(states, actions));
		QState currentState;
		for (int episode = 0; episodes == -1 || episode < episodes; episode++) {
			currentState = getInitialState();
			for (int iteration = 0; iteration < iterations; iteration++) {
				currentState = updateCurrentState(currentState, episode, iteration);
				if (isEndOfEpisode(currentState)) {
					logger.info("End of iteration: " + currentState);
					break;
				}
			}
			updateConstants(episode);
			logger.info("******* End of episode: " + episode + " *******");
			System.out.println(matrixUpdater.getMatrix());
			System.out.println(".");
		}
	}

	private QState updateCurrentState(QState current, int episode, int iteration) {
		try {
			QAction action = chooseAction(current);
			QState next = current.apply(action);
			float reward = getReward(next);
			matrixUpdater.updateValue(current, action, reward);
			debugStateUpdate(current, action, next, reward, episode, iteration);
			return next;
		} catch (NotApplicableException e) {
			System.out.println("Action not applicable! Skipping");
			return current;
		}
	}

	private void debugInitialParameters(float alpha, float gamma, int episodes, int iterations) {
		logger.info("Alpha: " + alpha);
		logger.info("Gamma: " + gamma);
		logger.info("Episodes: " + episodes);
		logger.info("Iterations: " + iterations);
	}

	private void debugStateUpdate(QState current, QAction action, QState next, float reward, int episode, int iteration) {
		logger.info("===============| episode: " + episode + " / iteration: " + iteration + " |===================");
		logger.info("Current State: [" + current + "]" );
		logger.info("Action: " + action);
		logger.info("Next State: [" + next + "]");
		logger.info("Reward: " + reward);
		logger.info("Q(S,A) = " + matrixUpdater.getMatrix().get(current, action));
	}

	protected QMatrix getMatrix() {
		return matrixUpdater.getMatrix();
	}

	protected void updateConstants(int episode) {
		if (epsilon > 0.2) {
			epsilon -= 0.01f;
		}
	}
	
	protected abstract QAction chooseAction(QState state);

	protected abstract float getReward(QState next);

	protected abstract QState getInitialState();

	protected abstract boolean isEndOfEpisode(QState state);

}
