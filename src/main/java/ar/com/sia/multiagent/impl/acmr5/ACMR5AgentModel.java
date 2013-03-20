package ar.com.sia.multiagent.impl.acmr5;

import ar.com.sia.multiagent.base.AgentModel;
import ar.com.sia.multiagent.base.handle.Joint;
import ar.com.sia.multiagent.base.handle.ProximitySensor;

public class ACMR5AgentModel extends AgentModel {

	public ACMR5AgentModel(String agentName) {
		super(agentName);
	}

	@Override
	protected void initialize() {
		add(new Joint("vJoint", 0.72f));
		add(new ProximitySensor("proxSensor"));
		setSteering(new ACMR5Steering());
	}
}
