package ar.com.sia.multiagent.impl.cuboid;

import ar.com.sia.multiagent.base.Agent;
import ar.com.sia.multiagent.base.AgentModel;
import ar.com.sia.multiagent.base.api.Perception;
import ar.com.sia.multiagent.base.handle.Handle;
import ar.com.sia.multiagent.base.handle.ProximitySensor;
import ar.com.sia.multiagent.base.handle.RotationalJoint;
import ar.com.sia.util.MathUtil;

public class CuboidModel extends AgentModel {

	public CuboidModel(Agent agent) {
		super(agent);
	}

	@Override
	protected void initialize(Handle mainHandle) {
		mainHandle.addChild(new ProximitySensor("proxSensor"));
		for (int wheel = 0; wheel < 4; wheel++) {
			Handle handle = new RotationalJoint("joint" + wheel);
			handle.addChild(new Handle("wheel" + wheel));
			mainHandle.addChild(handle);
		}
		setSteering(new CuboidSteering(this));
	}

	@Override
	public Perception sense() {
		ProximitySensor sensor = getProximitySensor();
		if (sensor.sensingObstacle()) {
			float distance = MathUtil.module(sensor.readDetectedPoint());
			return new CuboidPerception(distance);
		}
		return new CuboidPerception();
	}

	public ProximitySensor getProximitySensor() {
		return (ProximitySensor) getHandle("proxSensor");
	}

	public RotationalJoint getWheel(int index) {
		return (RotationalJoint) getHandle("joint" + index);
	}
}
