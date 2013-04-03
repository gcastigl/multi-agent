package ar.com.sia.multiagent.impl.cuboid;

import java.util.LinkedList;
import java.util.List;

import ar.com.sia.multiagent.base.OneShotAction;
import ar.com.sia.multiagent.base.SimulationServer;
import ar.com.sia.multiagent.base.Steering;
import ar.com.sia.multiagent.base.handle.Handle;
import ar.com.sia.multiagent.base.handle.RotationalJoint;

public class CuboidSteering extends Steering<CuboidModel> {

	private static final float WHEEL_VELOCITY = 50f;
	private static final SimulationServer simulationServer = SimulationServer.getInstance();

	private List<RotationalJoint> wheels = new LinkedList<RotationalJoint>();

	public CuboidSteering(CuboidModel model) {
		super(model);
		wheels.add(model.getWheel(0));
		wheels.add(model.getWheel(1));
		wheels.add(model.getWheel(2));
		wheels.add(model.getWheel(3));
	}

	private void setVelocity(final float velocity) {
		simulationServer.execute(new OneShotAction() {
			@Override
			public void execute() {
				for (RotationalJoint wheel : wheels) {
					wheel.setVelocity(velocity);
				}
			}
		});
	}

	@Override
	public void advance() {
		setVelocity(WHEEL_VELOCITY);
	}

	@Override
	public void stop() {
		setVelocity(0);
	}

	@Override
	public void rotate(final float alpha, final  float beta, final float gamma) {
		simulationServer.execute(new OneShotAction() {
			@Override
			public void execute() {
				Handle mainHanlde = getModel().getMainHandle();
				mainHanlde.addOrientation(alpha, beta, gamma);
			}
		});
	}

}
