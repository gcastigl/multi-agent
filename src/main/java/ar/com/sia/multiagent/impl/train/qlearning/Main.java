package ar.com.sia.multiagent.impl.train.qlearning;

import java.util.Collection;
import java.util.LinkedList;

import ar.com.sia.algorithm.qlearning.QAction;
import ar.com.sia.algorithm.qlearning.QLearningAlgorithm;
import ar.com.sia.algorithm.qlearning.QState;
import ar.com.sia.multiagent.impl.train.qlearning.action.MoveForward;
import ar.com.sia.multiagent.impl.train.qlearning.action.RotateAction;
import ar.com.sia.multiagent.impl.train.qlearning.state.AgentQState;
import ar.com.sia.multiagent.impl.train.qlearning.state.Direction;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}
	
	public void run() {
		QLearningAlgorithm trainer = new QLearningAlgorithm(1, 100);
		AgentQState initialState = new AgentQState(0, 0, Direction.FRONT);
		AgentQState goalState = new AgentQState(10, 10, Direction.FRONT);
		trainer.run(initialState, goalState, getStates(), getActions(), new AgentStateUpdater());
	}
	
	private Collection<QState> getStates() {
		Collection<QState> states = new LinkedList<QState>();
		for (int row = 0; row < 10; row++) {
			for (int column = 0; column < 10; column++) {
				for (Direction dir : Direction.values()) {					
					states.add(new AgentQState(row, column, dir));
				}
			}
		}
		return states;
	}
	
	private Collection<QAction> getActions() {
		Collection<QAction> actions = new LinkedList<QAction>();
		actions.add(new RotateAction(true));
		actions.add(new RotateAction(false));
		actions.add(new MoveForward(0.5f));
		return actions;
	}

}
