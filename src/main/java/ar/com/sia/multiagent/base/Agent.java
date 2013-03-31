package ar.com.sia.multiagent.base;

import ar.com.sia.multiagent.base.api.Action;
import ar.com.sia.multiagent.base.api.Perception;
import ar.com.sia.multiagent.base.program.AgentProgram;

public class Agent extends RemoteApiClient {

	private String name;
	private AgentProgram agentProgram;
	private AgentModel model;

	public Agent(String name) {
		this.name = name;
	}
	
	public void setProgram(AgentProgram agentProgram) {
		this.agentProgram = agentProgram;
	}

	public void setModel(AgentModel model) {
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public AgentModel getModel() {
		return model;
	}

	public void start() {
		agentProgram.run(Agent.this);
		/*
		new Thread(new Runnable() {
			@Override
			public void run() {
				agentProgram.run(Agent.this);
			}
		}).start();
		*/
	}

	public Perception sense() {
		return model.sense();
	}

	public void execute(Action action) {
		action.apply(this);
	}
}
