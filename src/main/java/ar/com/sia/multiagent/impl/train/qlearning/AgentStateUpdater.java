package ar.com.sia.multiagent.impl.train.qlearning;

import ar.com.sia.algorithm.qlearning.QAction;
import ar.com.sia.algorithm.qlearning.QState;
import ar.com.sia.algorithm.qlearning.StateUpdater;
import ar.com.sia.multiagent.impl.train.qlearning.action.MoveForward;

public class AgentStateUpdater implements StateUpdater {

	@Override
	public QAction chooseAction(QState state) {
		return new MoveForward(0.5f);
	}

	@Override
	public float getReward(QState state, QState next) {
		return 0;
	}

}
