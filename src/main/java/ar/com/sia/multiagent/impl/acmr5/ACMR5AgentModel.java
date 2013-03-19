package ar.com.sia.multiagent.impl.acmr5;

import vrep.server.RemoteApi;
import ar.com.sia.multiagent.base.AgentModel;
import ar.com.sia.multiagent.base.handle.Joint;
import ar.com.sia.multiagent.base.handle.ProximitySensor;

public class ACMR5AgentModel extends AgentModel {

	public ACMR5AgentModel(RemoteApi remoteApi, String agentName) {
		super(remoteApi, new ACMR5Steering());
		add(new Joint(remoteApi, agentName, "vJoint", 0.72f));
		add(new ProximitySensor(remoteApi, agentName, "proxSensor"));
	}

}
