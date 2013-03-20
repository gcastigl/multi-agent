package ar.com.sia.multiagent.base.handle;

import vrep.server.FloatW;

public class Joint extends Handle {

	private Float maxTargetPosition;
	
	public Joint(String name) {
		this(name, null);
	}

	public Joint(String name, Float maxTargetPosition) {
		super(name);
		this.maxTargetPosition = maxTargetPosition;
	}

	public void setPosition(float position) {
		getRemoteApi().simxSetJointPosition(getHandle(), position, API_OP_MODE);
	}
	
	public float getMaxTargetPosition() {
		return maxTargetPosition;
	}

	public float getPosition() {
		FloatW positionParam = new FloatW(0);
		getRemoteApi().simxGetJointPosition(getHandle(), positionParam, API_OP_MODE);
		return positionParam.getValue();
	}
	
	public void setTargetPosition(float targetPosition) {
		getRemoteApi().simxSetJointTargetPosition(getHandle(), targetPosition, API_OP_MODE);
	}
	
	public void setForce(float force) {
		getRemoteApi().simxSetJointForce(getHandle(), force, API_OP_MODE);
	}
}
