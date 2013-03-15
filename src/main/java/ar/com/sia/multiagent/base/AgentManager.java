package ar.com.sia.multiagent.base;

import java.util.LinkedList;
import java.util.List;

import vrep.server.RemoteApi;

public class AgentManager {

	private List<Agent> activeAgents;
	private boolean finished;
	
	public AgentManager(RemoteApi remoteApi) {
		activeAgents = new LinkedList<Agent>();
	}
	
	public void register(Agent agent) {
		activeAgents.add(agent);
	}
	
	public void run() {
		long startTime, elapsedTime = 0;
		while (!finished) {
			startTime = System.currentTimeMillis();
			for (Agent agent : activeAgents) {
				agent.update(elapsedTime);
			}
			sleep(50);
			elapsedTime = System.currentTimeMillis() - startTime;
		}
	}
	
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setFinished() {
		this.finished = true;
	}
	
	public boolean isFinished() {
		return finished;
	}
}
