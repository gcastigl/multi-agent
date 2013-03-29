package ar.com.sia.multiagent.impl.train.qlearning;

import org.apache.log4j.Logger;

import ar.com.sia.algorithm.qlearning.QAction;
import ar.com.sia.algorithm.qlearning.QLearningAlgorithm;
import ar.com.sia.algorithm.qlearning.QMatrix;
import ar.com.sia.algorithm.qlearning.QState;
import ar.com.sia.multiagent.impl.train.qlearning.action.Walk;
import ar.com.sia.multiagent.impl.train.qlearning.state.AgentQState;
import ar.com.sia.multiagent.impl.train.qlearning.state.Direction;

public class AgentTrainer extends QLearningAlgorithm {

	private Logger logger = Logger.getLogger(AgentTrainer.class);
	
	@Override
	protected QAction chooseAction(QState state) {
		AgentQState agentState = (AgentQState) state;
		if (Math.random() < epsilon) {
			return new Walk(Direction.values()[(int) (Math.random() * 4)], 0.5f);
		}
		QAction bestProfitAction = null;
		Float bestProfitValue = null;
		QMatrix matrix = getMatrix();
		for (QAction action : matrix.getActions()) {
			float value = matrix.get(agentState, action);
			logger.debug("\t\t> " + action + " = " + value);
			if (bestProfitAction == null || value > bestProfitValue) {
				bestProfitAction = action;
				bestProfitValue = value;
			}
			
		}
		return bestProfitAction;
	}

	@Override
	protected float getReward(QState next) {
		AgentQState nextAgentState = (AgentQState) next;
		if (nextAgentState.getColumn() == 5 && nextAgentState.getRow() == 5) {
			return 100;
		}
		if (nextAgentState.getColumn() < 0 || nextAgentState.getRow() < 0) {
			return -100;
		}
		if (nextAgentState.getColumn() > 5 || nextAgentState.getRow() > 5) {
			return -100;
		}
		return 0;
	}

	@Override
	protected QState getInitialState() {
		return new AgentQState(0, 0);
	}

	@Override
	protected boolean isEndOfEpisode(QState state) {
		AgentQState nextAgentState = (AgentQState) state;
		if (nextAgentState.getColumn() < 0 || nextAgentState.getRow() < 0) {
			return true;
		}
		if (nextAgentState.getColumn() > 5 || nextAgentState.getRow() > 5) {
			return true;
		}
		return false;
	}
	
}
