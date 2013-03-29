package ar.com.sia.multiagent.impl;

import ar.com.sia.algorithm.qlearning.QAction;
import ar.com.sia.algorithm.qlearning.QMatrix;
import ar.com.sia.algorithm.qlearning.QState;
import ar.com.sia.multiagent.base.Agent;
import ar.com.sia.multiagent.base.Brain;
import ar.com.sia.multiagent.impl.train.qlearning.state.AgentQState;

public class QLearningBrain implements Brain {

	private QMatrix movementMatrix;
	private Agent agent;
	
	public QLearningBrain(QMatrix movementMatrix) {
		this.movementMatrix = movementMatrix;
	}
	
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@Override
	public void start() {
		
	}

	private void findBestAction() {
		QState currentState = getCurrentState();
		for (QAction action : movementMatrix.getActions()) {
			float profit = movementMatrix.get(currentState, action);
			
		}
	}
	
	private QState getCurrentState() {
		float[] position = agent.getModel().getPosition();
		return new AgentQState((int) position[0], (int) position[1]);
	}
}
