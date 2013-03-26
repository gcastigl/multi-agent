package ar.com.sia.multiagent.base.handle;


public class RotationalJoint extends Joint {

	private float velocity;

	public RotationalJoint(String name) {
		super(name);
		velocity = 0;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
		getRemoteApi().simxSetJointTargetVelocity(getHandle(), adjustValue(velocity), MODE_NON_BLOCKING);
	}

	public void rotate(int angles, int modeType) {
		int currentPosition = (int) getTargetPosition();
		currentPosition += angles;
		if (currentPosition > 180) {
			currentPosition = -180 + (currentPosition % 180);  
		} else if (currentPosition < -180) {
			currentPosition = 180 - (currentPosition % 180);
		}
		setTargetPosition(currentPosition, modeType);
	}
	
	public float getVelocity() {
		return velocity;
	}
}
