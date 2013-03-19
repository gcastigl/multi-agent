package ar.com.sia.multiagent.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import vrep.server.RemoteApi;
import ar.com.sia.multiagent.base.handle.Joint;
import ar.com.sia.multiagent.base.handle.Sensor;

public abstract class AgentModel extends RemoteApiClient {

	private static final Logger logger = Logger.getLogger(AgentModel.class);

	private Map<String, Joint> joints;
	private Map<String, Sensor> sensors;
	private Steering steering;

	public AgentModel(RemoteApi remoteApi, Steering steering) {
		super(remoteApi);
		joints = new HashMap<String, Joint>();
		sensors = new HashMap<String, Sensor>();
		this.steering = steering;
		steering.setModel(this);
	}

	protected void add(Joint joint) {
		logger.trace("Loaded joint " + joint.getSimpleName());
		joints.put(joint.getSimpleName(), joint);
	}
	
	protected void add(Sensor sensor) {
		logger.trace("Loaded sensor " + sensor.getSimpleName());
		sensors.put(sensor.getSimpleName(), sensor);
	}
	
	public Sensor getSensor(String name) {
		return sensors.get(name);
	}

	public Joint getJoint(String name) {
		return joints.get(name);
	}
	
	public Steering getSteering() {
		return steering;
	}
}
