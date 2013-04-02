package ar.com.sia.multiagent.impl.cuboid.program;

import ar.com.sia.multiagent.base.Agent;
import ar.com.sia.multiagent.base.api.Action;

public class RotateAction implements Action {

	private float degrees;

	public RotateAction(float degrees) {
		this.degrees = degrees;
	}

	@Override
	public void apply(Agent agent) {
		agent.getModel().getSteering().rotate(0, 0, 90);
	}

	@Override
	public String toString() {
		return "Rotate: " + degrees;
	}
}
