package ar.com.sia.multiagent.impl.acmr5.state;

import ar.com.sia.multiagent.base.AgentState;
import ar.com.sia.multiagent.base.handle.Joint;
import ar.com.sia.multiagent.base.handle.ProximitySensor;

public class ACMR5WanderState extends AgentState {

	@Override
	public void enter() {
		((Joint) agent.getModel().getHandle("vJoint")).setTargetPosition(0.75f);
	}

	@Override
	public void update(long elapsedTime) {
		agent.getModel().getSteering().advance();
		((ProximitySensor) agent.getModel().getHandle("proxSensor")).read();
	}

}
