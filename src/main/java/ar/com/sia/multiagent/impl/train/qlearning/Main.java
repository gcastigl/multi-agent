package ar.com.sia.multiagent.impl.train.qlearning;

import java.util.Collection;
import java.util.LinkedList;

import ar.com.sia.algorithm.qlearning.QAction;
import ar.com.sia.algorithm.qlearning.QLearningAlgorithm;
import ar.com.sia.algorithm.qlearning.QState;
import ar.com.sia.multiagent.impl.train.qlearning.action.Walk;
import ar.com.sia.multiagent.impl.train.qlearning.state.AgentQState;
import ar.com.sia.multiagent.impl.train.qlearning.state.Direction;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	public void run() {
		QLearningAlgorithm trainer = new AgentTrainer();
		trainer.run(getStates(), getActions(), -1, 100);
	}

	private Collection<QState> getStates() {
		Collection<QState> states = new LinkedList<QState>();
		for (int row = 0; row < 10; row++) {
			for (int column = 0; column < 10; column++) {
				states.add(new AgentQState(row, column));
			}
		}
		return states;
	}

	private Collection<QAction> getActions() {
		Collection<QAction> actions = new LinkedList<QAction>();
		actions.add(new Walk(Direction.FRONT, 0.5f));
		actions.add(new Walk(Direction.BACK, 0.5f));
		actions.add(new Walk(Direction.LEFT, 0.5f));
		actions.add(new Walk(Direction.RIGHT, 0.5f));
		return actions;
	}

}
