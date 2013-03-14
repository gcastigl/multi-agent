package ar.edu.itba.sia.test.message;
import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.sia.agent.message.simple.ReceiverAgent;
import ar.edu.itba.sia.agent.message.simple.SenderAgent;
import ar.edu.itba.sia.init.AgentCmdAgentLineArgument;
import ar.edu.itba.sia.init.JadeInitializer;

public class MessagingAgentsExample {

	public static void main(String[] args) {
		JadeInitializer jade = new JadeInitializer();
		List<AgentCmdAgentLineArgument> agents = new LinkedList<>();
		agents.add(new AgentCmdAgentLineArgument("john", SenderAgent.class, "jack"));
		agents.add(new AgentCmdAgentLineArgument("jack", ReceiverAgent.class));
		jade.setAgents(agents);
		jade.run();
	}
}
