package ar.com.sia.multiagent.impl.epuck;

import ar.com.sia.multiagent.base.Steering;
import ar.com.sia.multiagent.base.handle.RotationalJoint;

public class EPuckSteering extends Steering<EPuckModel> {
	
	private RotationalJoint rightWheel;
	private RotationalJoint leftWheel;
	
	public EPuckSteering(EPuckModel model) {
		super(model);
		rightWheel = getModel().getRightWheel();
		leftWheel = getModel().getLeftWheel();
		rightWheel.setTargetPosition(0, MODE_NON_BLOCKING);
		leftWheel.setTargetPosition(0, MODE_NON_BLOCKING);
	}

	@Override
	public void advance() {
		rightWheel.setVelocity(100);
		leftWheel.setVelocity(100);
		getRemoteApi().simxPauseCommunication(true);
		rightWheel.rotate(-100, MODE_NON_BLOCKING);
		leftWheel.rotate(-100, MODE_NON_BLOCKING);
		getRemoteApi().simxPauseCommunication(false);
		System.out.println("ciclo " + rightWheel.getPosition());
	}

	@Override
	public void rotate(float degrees) {
		getRemoteApi().simxPauseCommunication(true);
		if (degrees > 0) {
			leftWheel.setVelocity(0);
			rightWheel.rotate(95, MODE_BLOCKING);
			rightWheel.rotate(95, MODE_BLOCKING);
		} else {
			leftWheel.setVelocity(100);
			leftWheel.rotate(95, MODE_BLOCKING);
			leftWheel.rotate(95, MODE_BLOCKING);
		}
		getRemoteApi().simxPauseCommunication(false);
	}
}
