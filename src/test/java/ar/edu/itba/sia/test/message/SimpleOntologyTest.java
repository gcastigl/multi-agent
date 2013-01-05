package ar.edu.itba.sia.test.message;

import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.sia.agent.message.ontology.ReaderAgent;
import ar.edu.itba.sia.agent.message.ontology.WriterAgent;
import ar.edu.itba.sia.init.AgentCmdAgentLineArgument;
import ar.edu.itba.sia.init.JadeInitializer;

public class SimpleOntologyTest {

	public static void main(String[] args) {
		JadeInitializer jade = new JadeInitializer();
		jade.setName("foo-plataform");
		List<AgentCmdAgentLineArgument> agents = new LinkedList<>();
		agents.add(new AgentCmdAgentLineArgument("writer", WriterAgent.class, "reader1", "reader2", "reader3"));
		agents.add(new AgentCmdAgentLineArgument("reader1", ReaderAgent.class));
		agents.add(new AgentCmdAgentLineArgument("reader2", ReaderAgent.class));
		agents.add(new AgentCmdAgentLineArgument("reader3", ReaderAgent.class));
		jade.setAgents(agents);
		jade.run();
	}

}
