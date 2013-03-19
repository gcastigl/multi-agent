package ar.com.sia.multiagent.base.handle;

import vrep.server.BoolW;
import vrep.server.FloatWA;
import vrep.server.RemoteApi;

public class ProximitySensor extends Sensor {

	public ProximitySensor(RemoteApi remoteApi, String agentName, String name) {
		super(remoteApi, agentName, name);
	}

	public boolean read() {
		BoolW detectionState = new BoolW(false);
		remoteApi.simxReadProximitySensor(getHandle(), detectionState, null, null, null, API_OP_MODE);
		return detectionState.getValue();
	}
	
	public float[] readDetectedPoint() {
		BoolW detectionState = new BoolW(false);
		FloatWA detectedPoint = new FloatWA(0);
		remoteApi.simxReadProximitySensor(getHandle(), detectionState, detectedPoint, null, null, API_OP_MODE);
		if (!detectionState.getValue()) {
			return null;
		}
		return detectedPoint.getArray();
	}
}
