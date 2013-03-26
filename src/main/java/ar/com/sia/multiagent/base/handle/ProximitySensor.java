package ar.com.sia.multiagent.base.handle;

import vrep.server.BoolW;
import vrep.server.FloatWA;

public class ProximitySensor extends Sensor {

	public ProximitySensor(String name) {
		super(name);
	}

	public boolean sensingObstacle() {
		BoolW detectionState = new BoolW(false);
		getRemoteApi().simxReadProximitySensor(getHandle(), detectionState, null, null, null, MODE_BLOCKING);
		return detectionState.getValue();
	}
	
	public float[] readDetectedPoint() {
		BoolW detectionState = new BoolW(false);
		FloatWA detectedPoint = new FloatWA(0);
		getRemoteApi().simxReadProximitySensor(getHandle(), detectionState, detectedPoint, null, null, MODE_BLOCKING);
		if (!detectionState.getValue()) {
			return null;
		}
		return detectedPoint.getArray();
	}
}
