package ar.com.sia.multiagent.base.handle;

import vrep.server.FloatW;
import vrep.server.RemoteApi;

public class JointHandle extends Handle {

	private Float maxTargetPosition;
	
	public JointHandle(RemoteApi remoteApi, String agentName, String name) {
		this(remoteApi, agentName, name, null);
	}

	public JointHandle(RemoteApi remoteApi, String agentName, String name, Float maxTargetPosition) {
		super(remoteApi, agentName, name);
		this.maxTargetPosition = maxTargetPosition;
	}

	public void setPosition(float position) {
		remoteApi.simxSetJointPosition(getHandle(), position, API_OP_MODE);
	}
	
	public float getMaxTargetPosition() {
		return maxTargetPosition;
	}

	public float getPosition() {
		FloatW positionParam = new FloatW(0);
		remoteApi.simxGetJointPosition(getHandle(), positionParam, API_OP_MODE);
		return positionParam.getValue();
	}
	
	public void setTargetPosition(float targetPosition) {
		remoteApi.simxSetJointTargetPosition(getHandle(), targetPosition, API_OP_MODE);
	}
	
	public void setForce(float force) {
		remoteApi.simxSetJointForce(getHandle(), force, API_OP_MODE);
	}
}
