package ar.com.sia.multiagent.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import ar.com.sia.multiagent.base.handle.Handle;

public abstract class AgentModel extends RemoteApiClient {

	private static final Logger logger = Logger.getLogger(AgentModel.class);

	private Map<String, Handle> handles;
	private Steering steering;
	private String agentName;

	public AgentModel(String agentName) {
		this.agentName = agentName;
		handles = new HashMap<String, Handle>();
		initialize();
	}

	protected abstract void initialize();
	
	public void setSteering(Steering steering) {
		this.steering = steering;
		steering.setModel(this);
	}

	protected void add(Handle handle) {
		handles.put(handle.getName(), handle);
		handle.fetch(agentName);
	}

	public Handle getHandle(String name) {
		return handles.get(name);
	}

	public Steering getSteering() {
		return steering;
	}
}
