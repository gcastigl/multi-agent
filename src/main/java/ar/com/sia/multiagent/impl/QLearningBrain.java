package ar.com.sia.multiagent.impl;

import ar.com.sia.algorithm.qlearning.QMatrix;
import ar.com.sia.multiagent.base.Agent;
import ar.com.sia.multiagent.base.Brain;

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

}
