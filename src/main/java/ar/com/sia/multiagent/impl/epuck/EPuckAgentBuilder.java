package ar.com.sia.multiagent.impl.epuck;

import ar.com.sia.multiagent.base.Agent;
import ar.com.sia.multiagent.base.api.AgentBuilder;

public class EPuckAgentBuilder implements AgentBuilder {

	@Override
	public Agent build(String name) {
		Agent agent = new Agent(name);
		agent.setModel(new EPuckModel(agent));
		return agent;
	}

}
