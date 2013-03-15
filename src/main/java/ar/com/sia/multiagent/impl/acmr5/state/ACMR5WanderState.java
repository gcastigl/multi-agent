package ar.com.sia.multiagent.impl.acmr5.state;

import ar.com.sia.multiagent.base.AgentState;

public class ACMR5WanderState extends AgentState {

	@Override
	public void initialize() {
		agent.getModel().getJoint("vJoint").setTargetPosition(0.75f);
	}

	@Override
	public void update(long elapsedTime) {
		agent.getModel().getSteering().advance();
	}

}
