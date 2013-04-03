package ar.com.sia.multiagent.impl.epuck;

import ar.com.sia.multiagent.base.Steering;
import ar.com.sia.multiagent.base.handle.RotationalJoint;
import ar.com.sia.util.MathUtil;

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
		rightWheel.setVelocity(MathUtil.toRadians(100));
		leftWheel.setVelocity(MathUtil.toRadians(100));
		getRemoteApi().simxPauseCommunication(true);
		getRemoteApi().simxPauseCommunication(false);
		System.out.println("ciclo " + rightWheel.getPosition());
	}

	@Override
	public void rotate(float alpha, float beta, float gamma) {
		if (gamma > 0) {
			leftWheel.setVelocity(0);
		} else {
			leftWheel.setVelocity(MathUtil.toRadians(100));
		}
	}
	
	@Override
	public void stop() {
		
	}
}
