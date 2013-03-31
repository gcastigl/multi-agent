package ar.com.sia.multiagent.impl.program;

import java.util.Collection;

import org.apache.log4j.Logger;

import ar.com.sia.multiagent.base.PerceptionState;
import ar.com.sia.multiagent.base.api.Action;
import ar.com.sia.multiagent.base.program.TrainerAgentProgram;
import ar.com.sia.multiagent.impl.cuboid.CuboidPerception;
import ar.com.sia.qlearning.QLearningMatrixUpdater;
import ar.com.sia.qlearning.QMatrix;
import ar.com.sia.util.MathUtil;

public class QLearningTrainer extends TrainerAgentProgram<PerceptionState> {

	private Logger logger = Logger.getLogger(QLearningTrainer.class);
	private QLearningMatrixUpdater matrixUpdater;
	
	private float alpha = 0.1f;
	private float gamma = 0.9f;
	private float epsilon = 0.8f;
	private Collection<Action> allActions;
	
	public QLearningTrainer(int episodes, int iterations, Collection<Action> allActions) {
		super(episodes, iterations);
		this.allActions = allActions;
		matrixUpdater = new QLearningMatrixUpdater(alpha, gamma, new QMatrix(allActions));
	}

	@Override
	protected void updateTraining(PerceptionState current, PerceptionState next, Action action) {
		float reward = getReward(next);
		matrixUpdater.updateValue(current, action, reward);
	}

	private float getReward(PerceptionState next) {
		CuboidPerception perception = (CuboidPerception) next.getPerception();
		if (perception.getDistanceToObject() < 1) {
			return -100;
		}
		return 0;
	}

	@Override
	protected PerceptionState getCurrentState() {
		return new PerceptionState(agent.sense());
	}

	@Override
	protected Action deliverate(PerceptionState state) {
		if (Math.random() < epsilon) {
			return MathUtil.random(allActions);
		}
		Action bestProfitAction = null;
		Float bestProfitValue = null;
		QMatrix matrix = matrixUpdater.getMatrix();
		for (Action action : matrix.getAllActions()) {
			float value = matrix.get(state, action);
			logger.debug("\t\t> " + action + " = " + value);
			if (bestProfitAction == null || value > bestProfitValue) {
				bestProfitAction = action;
				bestProfitValue = value;
			}
			
		}
		return bestProfitAction;
	}

	@Override
	protected boolean isEndOfEpisode(PerceptionState state) {
		return ((CuboidPerception) state.getPerception()).getDistanceToObject() < 0.5f;
	}

	/*

	private void debugInitialParameters(float alpha, float gamma, int episodes, int iterations) {
		logger.info("Alpha: " + alpha);
		logger.info("Gamma: " + gamma);
		logger.info("Episodes: " + episodes);
		logger.info("Iterations: " + iterations);
	}

	private void debugStateUpdate(QState current, Action action, QState next, float reward, int episode, int iteration) {
		logger.info("===============| episode: " + episode + " / iteration: " + iteration + " |===================");
		logger.info("Current State: [" + current + "]" );
		logger.info("Action: " + action);
		logger.info("Next State: [" + next + "]");
		logger.info("Reward: " + reward);
		logger.info("Q(S,A) = " + matrixUpdater.getMatrix().get(current, action));
	}
	*/
}
