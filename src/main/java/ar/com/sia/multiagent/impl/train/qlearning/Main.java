package ar.com.sia.multiagent.impl.train.qlearning;

import java.util.Collection;
import java.util.LinkedList;

import ar.com.sia.algorithm.qlearning.QLearningAlgorithm;
import ar.com.sia.algorithm.qlearning.QState;
import ar.com.sia.multiagent.base.Agent;
import ar.com.sia.multiagent.impl.train.qlearning.state.AgentQState;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}
	
	public void run() {
		QLearningAlgorithm trainer = new QLearningAlgorithm(1, 1);
		Agent agent = new Agent("test");
		AgentQState initialState = new AgentQState(agent, 0, 0);
		trainer.run(initialState, goalState, states, actions, stateUpdater);
	}
	
	private Collection<QState> getStates(Agent agent) {
		Collection<QState> states = new LinkedList<QState>();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				states.add(new AgentQState(agent, row, column));
			}
		}
	}
}
