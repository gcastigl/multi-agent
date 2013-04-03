package ar.com.sia.multiagent.base.handle;

import vrep.server.FloatW;

public class Joint extends Handle {

	protected float targetPosition;

	public Joint(String name) {
		super(name);
	}

	public void setPosition(float position) {
		getRemoteApi().simxSetJointPosition(getHandle(), position, MODE_NON_BLOCKING);
	}

	public float getPosition() {
		FloatW positionParam = new FloatW(0);
		getRemoteApi().simxGetJointPosition(getHandle(), positionParam, MODE_BLOCKING);
		return positionParam.getValue();
	}
	
	public void setTargetPosition(float targetPosition, int modeType) {
		this.targetPosition = targetPosition; 
		getRemoteApi().simxSetJointTargetPosition(getHandle(), targetPosition, modeType);
	}
	
	public float getTargetPosition() {
		return targetPosition;
	}
	
	public void setMaxForce(float force) {
		getRemoteApi().simxSetJointForce(getHandle(), force, MODE_NON_BLOCKING);
	}

}
