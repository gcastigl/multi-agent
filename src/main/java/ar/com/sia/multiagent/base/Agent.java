package ar.com.sia.multiagent.base;

public class Agent extends RemoteApiClient {

	private String name;
	private Brain brain;
	private AgentModel model;
	private AgentState state;

	public Agent(String name) {
		this.name = name;
	}

	public void setBrain(Brain brain) {
		this.brain = brain;
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
