package ar.com.sia.multiagent.impl.acmr5;

import ar.com.sia.multiagent.base.AgentModel;
import ar.com.sia.multiagent.base.handle.JointHandle;
import vrep.server.RemoteApi;

public class ACMR5AgentModel extends AgentModel {

	public ACMR5AgentModel(RemoteApi remoteApi, String agentName) {
		super(remoteApi, new ACMR5Steering());
		add(new JointHandle(remoteApi, agentName, "vJoint", 0.72f));
	}

}
