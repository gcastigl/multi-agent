package ar.com.sia.multiagent.impl.cuboid.program;

import java.util.Collection;

import org.apache.log4j.Logger;

import ar.com.sia.multiagent.base.SimulationServer;
import ar.com.sia.multiagent.base.api.Action;
import ar.com.sia.multiagent.base.program.TrainerAgentProgram;
import ar.com.sia.multiagent.impl.cuboid.CuboidPerception;
import ar.com.sia.qlearning.QLearningMatrixUpdater;
import ar.com.sia.qlearning.QMatrix;
import ar.com.sia.util.MathUtil;

public class CuboidQLearningTrainer extends TrainerAgentProgram<CuboidState> {

	private Logger logger = Logger.getLogger(CuboidQLearningTrainer.class);
	private QLearningMatrixUpdater matrixUpdater;
	
	private float alpha = 0.1f;
	private float gamma = 0.9f;
	private float epsilon = 0.8f;
	private Collection<Action> allActions;
	
	public CuboidQLearningTrainer(int episodes, int iterations, Collection<Action> allActions) {
		super(episodes, iterations);
		this.allActions = allActions;
		matrixUpdater = new QLearningMatrixUpdater(alpha, gamma, new QMatrix(allActions));
		logger.info("Alpha: " + alpha);
		logger.info("Gamma: " + gamma);
		logger.info("Episodes: " + episodes);
		logger.info("Iterations: " + iterations);
	}

	@Override
	protected void setupEpisode() {
		SimulationServer.getInstance().restart();
	}
	
	@Override
	protected void updateTraining(CuboidState current, CuboidState next, Action action) {
		float reward = getReward(next);
		matrixUpdater.updateValue(current, action, reward);
		logger.info("Reward: " + reward);
		logger.info("Q(S,A) = " + matrixUpdater.getMatrix().get(current, action));
	}

	private float getReward(CuboidState next) {
		CuboidPerception perception = (CuboidPerception) next.getPerception();
		if (perception.isSensing() && perception.getDistanceToObject() < 0.3f) {
			return -100;
		}
		return 0;
	}

	@Override
	protected CuboidState getCurrentState() {
		return new CuboidState((CuboidPerception) agent.sense());
	}

	@Override
	protected Action deliverate(CuboidState state) {
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
	protected boolean isEndOfEpisode(CuboidState state) {
		if (!state.getPerception().isSensing()) {
			return false; 
		}
		return state.getPerception().getDistanceToObject() < 0.1f;
	}

}
