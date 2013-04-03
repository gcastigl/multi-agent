package ar.com.sia.multiagent.impl.epuck;

import ar.com.sia.multiagent.base.Agent;
import ar.com.sia.multiagent.base.AgentModel;
import ar.com.sia.multiagent.base.api.Perception;
import ar.com.sia.multiagent.base.handle.Handle;
import ar.com.sia.multiagent.base.handle.ProximitySensor;
import ar.com.sia.multiagent.base.handle.RotationalJoint;

public class EPuckModel extends AgentModel {

	public enum Sensor {
		LEFT(1), FRONT_LEFT(3), FRONT_RIGHT(4), RIGHT(6);
		private int id;
	
		private Sensor(int id) {
			this.id = id;
		}
	};
	
	public EPuckModel(Agent agent) {
		super(agent);
	}

	@Override
	protected void initialize(Handle mainHandle) {
		mainHandle.addChild(new RotationalJoint("rightJoint"));
		mainHandle.addChild(new RotationalJoint("leftJoint"));
		mainHandle.addChild(new ProximitySensor("proxSensor" + Sensor.LEFT.id));
		mainHandle.addChild(new ProximitySensor("proxSensor" + Sensor.FRONT_LEFT.id));
		mainHandle.addChild(new ProximitySensor("proxSensor" + Sensor.FRONT_RIGHT.id));
		mainHandle.addChild(new ProximitySensor("proxSensor" + Sensor.RIGHT.id));
		setSteering(new EPuckSteering(this));
	}

	@Override
	public Perception sense() {
		throw new IllegalStateException("Not implemented");
	}

	public RotationalJoint getRightWheel() {
		return (RotationalJoint) getHandle("rightJoint");
	}

	public RotationalJoint getLeftWheel() {
		return (RotationalJoint) getHandle("leftJoint");
	}
	
	public ProximitySensor getProximitySensor(Sensor sensor) {
		return (ProximitySensor) getHandle("proxSensor" + sensor.id);
	}
	
}
