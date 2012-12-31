package ar.edu.itba.sia.init;

import jade.Boot;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class JadeInitializer {

	private List<String> arguments;

	public JadeInitializer() {
		arguments = new LinkedList<>();
	}

	public void setAgents(AgentCmdAgentLineArgument[] agents) {
		String[] agentsStrings = new String[agents.length];
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
