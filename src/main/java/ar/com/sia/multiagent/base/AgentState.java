package ar.com.sia.multiagent.base;

public abstract class AgentState {

	protected Agent agent;

	public final void enter(Agent agent) {
		this.agent = agent;
		initialize();
	}

	public void initialize() {
	}

	public abstract void update(long elapsedTime);

	public void exit() {
	}

}
