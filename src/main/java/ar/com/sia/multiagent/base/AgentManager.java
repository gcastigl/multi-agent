package ar.com.sia.multiagent.base;

import java.util.LinkedList;
import java.util.List;

public class AgentManager {

	private List<Agent> agents;
	private boolean finished;

	public AgentManager() {
		agents = new LinkedList<Agent>();
	}

	public void register(Agent agent) {
		agents.add(agent);
	}

	public void run() {
		for (Agent agent : agents) {
			agent.start();
		}
	}

	public void setFinished() {
		this.finished = true;
	}

	public boolean isFinished() {
		return finished;
	}
}
