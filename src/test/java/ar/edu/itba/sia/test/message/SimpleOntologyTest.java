package ar.edu.itba.sia.test.message;

import ar.edu.itba.sia.agent.message.ontology.ReaderAgent;
import ar.edu.itba.sia.agent.message.ontology.WriterAgent;
import ar.edu.itba.sia.init.AgentCmdAgentLineArgument;
import ar.edu.itba.sia.init.JadeInitializer;

public class SimpleOntologyTest {

	public static void main(String[] args) {
		JadeInitializer jade = new JadeInitializer();
		AgentCmdAgentLineArgument[] agents = new AgentCmdAgentLineArgument[4];
		agents[0] = new AgentCmdAgentLineArgument("writer", WriterAgent.class, "reader1", "reader2", "reader3");
		agents[1] = new AgentCmdAgentLineArgument("reader1", ReaderAgent.class);
		agents[2] = new AgentCmdAgentLineArgument("reader2", ReaderAgent.class);
		agents[3] = new AgentCmdAgentLineArgument("reader3", ReaderAgent.class);
		jade.setAgents(agents);
		jade.run();
	}

}
