package ar.com.sia.multiagent.base;

import java.util.HashMap;
import java.util.Map;

import ar.com.sia.multiagent.base.api.Perception;
import ar.com.sia.multiagent.base.handle.Handle;

public abstract class AgentModel extends RemoteApiClient {

	private Map<String, Handle> handles;
	private Steering<? extends AgentModel> steering;
	private Agent agent;

	public AgentModel(Agent agent) {
		this.agent = agent;
		handles = new HashMap<String, Handle>();
		initialize();
		Handle mainHandle = new Handle(null);
		mainHandle.fetch(agent.getName());
		handles.put(agent.getName(), mainHandle);
	}

	protected abstract void initialize();

	public void setSteering(Steering<? extends AgentModel> steering) {
		this.steering = steering;
	}

	protected void add(Handle handle) {
		handles.put(handle.getName(), handle);
		handle.fetch(agent.getName());
	}

	public Handle getHandle(String name) {
		return handles.get(name);
	}

	public Steering<? extends AgentModel> getSteering() {
		return steering;
	}

	public float[] getPosition() {
		return getMainHandle().getAbsolutePosition();
	}

	public void setPosition(float[] position) {
		getMainHandle().setAbsolutePosition(position);
	}

	public void addPosition(float x, float y, float z) {
		getMainHandle().addPosition(x, y, z);
	}
	
	public void addRotation(float alpha, float beta, float gamma) {
		getMainHandle().addOrientation(alpha, beta, gamma);
	}

	public float[] getRotation() {
		return getMainHandle().getOrientation();
	}
	
	public Handle getMainHandle() {
		return getHandle(agent.getName());
	}

	public abstract Perception sense();

}
