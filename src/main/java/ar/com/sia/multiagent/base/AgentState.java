package ar.com.sia.multiagent.base;

public abstract class AgentState {

	protected Agent agent;

	public final void initialize(Agent agent) {
		this.agent = agent;
		enter();
	}

	public void enter() {
	}

	public abstract void update(long elapsedTime);

	public void exit() {
	}

}
