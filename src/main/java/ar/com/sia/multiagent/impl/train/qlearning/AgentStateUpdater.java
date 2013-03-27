package ar.com.sia.multiagent.impl.train.qlearning;

import ar.com.sia.algorithm.qlearning.QAction;
import ar.com.sia.algorithm.qlearning.QState;
import ar.com.sia.algorithm.qlearning.StateUpdater;
import ar.com.sia.multiagent.impl.train.qlearning.action.MoveForward;
import ar.com.sia.multiagent.impl.train.qlearning.action.RotateAction;
import ar.com.sia.multiagent.impl.train.qlearning.state.AgentQState;

public class AgentStateUpdater implements StateUpdater {

	@Override
	public QAction chooseAction(QState state) {
		if (Math.random() > 0.5f) {
			return new MoveForward(0.5f);
		} else {
			return new RotateAction(Math.random() > 0.5f ? true : false);
		}
	}

	@Override
	public float getReward(QState state, QState next) {
		AgentQState nextAgentState = (AgentQState) next;
		if (nextAgentState.getColumn() < 0 || nextAgentState.getRow() < 0) {
			return -100;
		}
		if (nextAgentState.getColumn() > 10 || nextAgentState.getRow() > 10) {
			return -100;
		}
		if (nextAgentState.getColumn() == 10 || nextAgentState.getRow() == 10) {
			return 100;
		}
		return 0;
	}

}
