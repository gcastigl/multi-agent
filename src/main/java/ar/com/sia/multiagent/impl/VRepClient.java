package ar.com.sia.multiagent.impl;

import org.apache.log4j.Logger;

import ar.com.sia.multiagent.base.AgentManager;
import ar.com.sia.multiagent.base.ConnectionException;
import ar.com.sia.multiagent.base.SimulationServer;
import ar.com.sia.multiagent.impl.cuboid.program.CuboidTrainer;
import ar.com.sia.multiagent.impl.epuck.EPuckAgentBuilder;

public class VRepClient implements Runnable {
	
	private static final Logger logger = Logger.getLogger(VRepClient.class);
	
	public static void main(String[] args) {
		new VRepClient().run();
	}

	public void run() {
		logger.trace("Program started");
		SimulationServer server = SimulationServer.getInstance();
		try {
			server.connect("127.0.0.1", 19998);
		} catch (ConnectionException e) {
			logger.fatal("Failed connecting to remote API server");
			return;
		}
		server.start();
		logger.info("Number of objects in the scene: " + server.getAllObjectsHandle().length);
		new CuboidTrainer().run();
		server.stop();
		server.disconnect();
		logger.trace("Program ended");
	}

	private AgentManager getAgentmanager() {
		AgentManager agentManager = new AgentManager();
		// agentManager.register(ACMR5AgentBuilder.build("ACMR"));
		agentManager.register(new EPuckAgentBuilder().build("ePuck"));
		return agentManager;
	}
}
