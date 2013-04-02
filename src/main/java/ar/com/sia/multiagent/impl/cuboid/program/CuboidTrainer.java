package ar.com.sia.multiagent.impl.cuboid.program;

import java.util.Collection;
import java.util.LinkedList;

import ar.com.sia.multiagent.base.Agent;
import ar.com.sia.multiagent.base.api.Action;
import ar.com.sia.multiagent.impl.cuboid.CuboidAgentBuilder;

public class CuboidTrainer {

	public void run() {
		Agent agent = new CuboidAgentBuilder().build("Cuboid");
		agent.setProgram(new CuboidQLearningTrainer(100, 100, getActions()));
		agent.start();
	}

	private Collection<Action> getActions() {
		Collection<Action> actions = new LinkedList<Action>();
		actions.add(new WalkAction());
		actions.add(new RotateAction(90f));
		actions.add(new RotateAction(-90f));
		return actions;
	}
}
