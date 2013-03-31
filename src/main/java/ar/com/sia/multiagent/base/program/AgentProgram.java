package ar.com.sia.multiagent.base.program;

import ar.com.sia.multiagent.base.Agent;

public abstract class AgentProgram {

	protected Agent agent;

	public final void run(Agent agent) {
		this.agent = agent;
		start();
	}

	protected abstract void start();

}
