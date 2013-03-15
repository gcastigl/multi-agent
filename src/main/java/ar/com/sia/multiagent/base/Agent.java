package ar.com.sia.multiagent.base;

import vrep.server.RemoteApi;

public class Agent extends RemoteApiClient {

	private String name;
	private AgentModel model;
	private AgentState state;

	public Agent(RemoteApi remoteApi, AgentModel model, String name) {
		super(remoteApi);
		this.name = name;
		this.model = model;
	}

	public void update(long elapsedTime) {
		state.update(elapsedTime);
	}

	public String getName() {
		return name;
	}

	public AgentModel getModel() {
		return model;
	}

	public void setState(AgentState state) {
		if (state != null) {
			state.exit();
		}
		this.state = state;
		state.enter(this);
	}

	public AgentState getState() {
		return state;
	}

}
