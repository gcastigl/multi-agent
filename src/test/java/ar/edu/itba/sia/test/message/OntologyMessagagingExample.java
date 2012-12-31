package ar.edu.itba.sia.test.message;

import ar.edu.itba.sia.agent.message.ontology.musicshop.CDTraderAgent;
import ar.edu.itba.sia.init.AgentCmdAgentLineArgument;
import ar.edu.itba.sia.init.JadeInitializer;

public class OntologyMessagagingExample {

	public static void main(String[] args) {
		JadeInitializer jade = new JadeInitializer();
		AgentCmdAgentLineArgument[] agents = new AgentCmdAgentLineArgument[1];
		agents[0] = new AgentCmdAgentLineArgument("john", CDTraderAgent.class, "jack");
		jade.setAgents(agents);
		jade.run();
	}
}
