package ar.com.sia.multiagent.base;

public class Agent extends RemoteApiClient {

	private String name;
	private AgentModel model;
	private AgentState state;

	public Agent(AgentModel model, String name) {
		this.name = name;
		this.model = model;
	}

	public void setModel(AgentModel model) {
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
		state.initialize(this);
	}

	public AgentState getState() {
		return state;
	}

}
