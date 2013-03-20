package ar.com.sia.multiagent.impl.acmr5;

import ar.com.sia.multiagent.base.Agent;
import ar.com.sia.multiagent.base.AgentModel;
import ar.com.sia.multiagent.impl.acmr5.state.ACMR5WanderState;

public class ACMR5AgentBuilder {

	public static Agent build(String name) {
		AgentModel model = new ACMR5AgentModel(name);
		Agent agent = new Agent(model, name);
		agent.setState(new ACMR5WanderState());
		return agent;
	}

	private ACMR5AgentBuilder() {
	}

}
