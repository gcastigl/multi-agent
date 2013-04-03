package ar.com.sia.multiagent.impl.cuboid.program;

import ar.com.sia.multiagent.base.Agent;
import ar.com.sia.multiagent.base.api.Action;

public class WalkAction implements Action {

	@Override
	public void apply(Agent agent) {
		agent.getModel().getSteering().advance();
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		agent.getModel().getSteering().stop();
	}
	
	@Override
	public String toString() {
		return "Walk";
	}

}
