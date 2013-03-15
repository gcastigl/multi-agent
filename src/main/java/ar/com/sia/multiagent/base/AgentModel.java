package ar.com.sia.multiagent.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import ar.com.sia.multiagent.base.handle.JointHandle;

import vrep.server.RemoteApi;

public abstract class AgentModel extends RemoteApiClient {

	private static final Logger logger = Logger.getLogger(AgentModel.class);

	private Map<String, JointHandle> joints;
	private Steering steering;

	public AgentModel(RemoteApi remoteApi, Steering steering) {
		super(remoteApi);
		joints = new HashMap<String, JointHandle>();
		this.steering = steering;
		steering.setModel(this);
		
	}

	protected void add(JointHandle joint) {
		logger.trace("Loaded joint " + joint.getName());
		joints.put(joint.getName(), joint);
	}
	
	public JointHandle getJoint(String name) {
		return joints.get(name);
	}
	
	public Steering getSteering() {
		return steering;
	}
}
