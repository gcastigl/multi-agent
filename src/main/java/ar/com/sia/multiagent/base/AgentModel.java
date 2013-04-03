package ar.com.sia.multiagent.base;

import ar.com.sia.multiagent.base.api.Perception;
import ar.com.sia.multiagent.base.handle.Handle;

public abstract class AgentModel extends RemoteApiClient {

	private Handle mainHandle;
	private Steering<? extends AgentModel> steering;

	public AgentModel(Agent agent) {
		mainHandle = new Handle(null);
		initialize(mainHandle);
		mainHandle.fetch(agent.getName());
	}

	protected abstract void initialize(Handle mainHandle);

	public void setSteering(Steering<? extends AgentModel> steering) {
		this.steering = steering;
	}

	public Handle getMainHandle() {
		return mainHandle;
	}

	public Handle getHandle(String name) {
		return mainHandle.getChild(name);
	}

	public Steering<? extends AgentModel> getSteering() {
		return steering;
	}

	public abstract Perception sense();

}
