package ar.edu.itba.sia.test.helloworld;
import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.sia.agent.helloworld.HelloWorldAgent;
import ar.edu.itba.sia.init.JadeInitializer;
import ar.edu.itba.sia.init.AgentCmdAgentLineArgument;

public class HellowWorldExample {

	public static void main(String[] args) {
		JadeInitializer jade = new JadeInitializer();
		List<AgentCmdAgentLineArgument> agents = new LinkedList<>();
		agents.add(new AgentCmdAgentLineArgument("Agent_1", HelloWorldAgent.class, "painting"));
		agents.add(new AgentCmdAgentLineArgument("Agent_2", HelloWorldAgent.class));
		jade.setAgents(agents);
		jade.run();
	}
}
