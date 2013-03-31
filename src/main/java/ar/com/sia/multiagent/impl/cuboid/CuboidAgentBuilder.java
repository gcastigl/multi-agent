package ar.com.sia.multiagent.impl.cuboid;

import ar.com.sia.multiagent.base.Agent;
import ar.com.sia.multiagent.base.api.AgentBuilder;

public class CuboidAgentBuilder implements AgentBuilder {

	@Override
	public Agent build(String name) {
		Agent agent = new Agent(name);
		agent.setModel(new CuboidModel(agent));
		return agent;
	}

}
