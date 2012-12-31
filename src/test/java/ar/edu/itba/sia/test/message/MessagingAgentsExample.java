package ar.edu.itba.sia.test.message;
import ar.edu.itba.sia.agent.message.simple.ReceiverAgent;
import ar.edu.itba.sia.agent.message.simple.SenderAgent;
import ar.edu.itba.sia.init.AgentCmdAgentLineArgument;
import ar.edu.itba.sia.init.JadeInitializer;

public class MessagingAgentsExample {

	public static void main(String[] args) {
		JadeInitializer jade = new JadeInitializer();
		AgentCmdAgentLineArgument[] agents = new AgentCmdAgentLineArgument[2];
		agents[0] = new AgentCmdAgentLineArgument("john",
				SenderAgent.class, "jack");
		agents[1] = new AgentCmdAgentLineArgument("jack",
				ReceiverAgent.class);
		jade.setAgents(agents);
		jade.run();
	}
}
