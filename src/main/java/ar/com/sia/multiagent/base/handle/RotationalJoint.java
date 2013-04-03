package ar.com.sia.multiagent.base.handle;

public class RotationalJoint extends Joint {

	private float velocity;

	public RotationalJoint(String name) {
		super(name);
		velocity = 0;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
		getRemoteApi().simxSetJointTargetVelocity(getHandle(), velocity, MODE_NON_BLOCKING);
	}

	public float getVelocity() {
		return velocity;
	}
}
