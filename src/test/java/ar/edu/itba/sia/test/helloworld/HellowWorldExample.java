package ar.edu.itba.sia.test.helloworld;
import ar.edu.itba.sia.agent.helloworld.HelloWorldAgent;
import ar.edu.itba.sia.init.JadeInitializer;
import ar.edu.itba.sia.init.AgentCmdAgentLineArgument;

public class HellowWorldExample {

	public static void main(String[] args) {
		JadeInitializer jade = new JadeInitializer();
		AgentCmdAgentLineArgument[] agents = new AgentCmdAgentLineArgument[2];
		agents[0] = new AgentCmdAgentLineArgument("Agent_1",
				HelloWorldAgent.class, "painting");
		agents[1] = new AgentCmdAgentLineArgument("Agent_2",
				HelloWorldAgent.class);
		jade.setAgents(agents);
		jade.run();
	}
}
