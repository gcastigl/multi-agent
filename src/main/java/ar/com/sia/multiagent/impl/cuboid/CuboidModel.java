package ar.com.sia.multiagent.impl.cuboid;

import ar.com.sia.multiagent.base.Agent;
import ar.com.sia.multiagent.base.AgentModel;
import ar.com.sia.multiagent.base.api.Perception;
import ar.com.sia.multiagent.base.handle.ProximitySensor;
import ar.com.sia.util.MathUtil;

public class CuboidModel extends AgentModel {

	public CuboidModel(Agent agent) {
		super(agent);
	}

	@Override
	protected void initialize() {
		add(new ProximitySensor("proxSensor"));
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
}
