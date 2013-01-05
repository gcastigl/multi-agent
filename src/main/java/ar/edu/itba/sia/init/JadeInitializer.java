package ar.edu.itba.sia.init;

import jade.Boot;

import java.util.Collection;
import java.util.LinkedList;

import org.apache.commons.lang3.StringUtils;

public class JadeInitializer {

	private Collection<String> arguments;

	public JadeInitializer() {
		arguments = new LinkedList<>();
	}

	public void setName(String name) {
		arguments.add("-name");
		arguments.add(name);
	}
	
	public void setAgents(Collection<AgentCmdAgentLineArgument> agents) {
		String[] agentsStrings = new String[agents.size()];
		int i = 0;
		for (AgentCmdAgentLineArgument agent : agents) {
			agentsStrings[i++] = agent.getCommand();
		}
		arguments.add("-agents");
		arguments.add(StringUtils.join(agentsStrings, ";"));
	}

	public void run() {
		Boot.main(arguments.toArray(new String[arguments.size()]));
	}

}
