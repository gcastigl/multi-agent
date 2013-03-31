package ar.com.sia.multiagent.impl.program;

import java.util.Collection;
import java.util.LinkedList;

import ar.com.sia.multiagent.base.Agent;
import ar.com.sia.multiagent.base.api.Action;
import ar.com.sia.multiagent.impl.cuboid.CuboidAgentBuilder;

public class Trainer {

	public void run() {
		Agent agent = new CuboidAgentBuilder().build("Cuboid");
		agent.setProgram(new QLearningTrainer(100, 100, getActions()));
		agent.start();
	}

	private Collection<Action> getActions() {
		Collection<Action> actions = new LinkedList<Action>();
		actions.add(new WalkAction());
		return actions;
	}
}
