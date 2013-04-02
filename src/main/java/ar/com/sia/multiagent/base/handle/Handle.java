package ar.com.sia.multiagent.base.handle;

import org.apache.log4j.Logger;

import vrep.server.FloatWA;
import vrep.server.IntW;
import vrep.server.RemoteApi;
import ar.com.sia.multiagent.base.RemoteApiClient;

public class Handle extends RemoteApiClient {

	private static final Logger logger = Logger.getLogger(Handle.class);

	protected final static float MAGIC_FACTOR = 57.3f;

	private Integer handle;
	private String name;

	public Handle(String name) {
		this.name = name;
		handle = null;
	}

	public int getHandle() {
		return handle;
	}

	public void setHandle(int handle) {
		this.handle = handle;
	}

	public String getName() {
		return name;
	}

	public float[] getAbsolutePosition() {
		FloatWA position = new FloatWA(0);
		getRemoteApi().simxGetObjectPosition(getHandle(), -1, position, MODE_BLOCKING);
		return position.getArray();
	}

	public void setAbsolutePosition(float[] position) {
		getRemoteApi().simxSetObjectPosition(getHandle(), -1, new FloatWA(position), MODE_BLOCKING);
	}

	public void addOrientation(float alpha, float beta, float gamma) {
		float[] orientation = getOrientation();
		orientation[0] += alpha;
		orientation[1] += beta;
		orientation[2] += gamma;
		setOrientation(orientation);
	}

	public void addPosition(float x, float y, float z) {
		float[] position = getAbsolutePosition();
		position[0] += x;
		position[1] += y;
		position[2] += z;
		setAbsolutePosition(position);
	}
	
	public void setOrientation(float[] orientation) {
		FloatWA eulerAngles = new FloatWA(orientation);
		getRemoteApi().simxSetObjectOrientation(getHandle(), getHandle(), eulerAngles, MODE_BLOCKING);
	}

	public float[] getOrientation() {
		FloatWA eulerAngles = new FloatWA(new float[] {});
		getRemoteApi().simxGetObjectOrientation(getHandle(), -1, eulerAngles, MODE_BLOCKING);
		return eulerAngles.getArray();
	}
	
	public int fetch(String agentName) {
		IntW handleParam = new IntW(0);
		String fullname = agentName + ((name == null) ? "" : "_" + name);
		int result = getRemoteApi().simxGetObjectHandle(fullname, handleParam, MODE_BLOCKING);
		if (result != RemoteApi.simx_error_noerror) {
			throw new IllegalStateException("Object handle for " + fullname + " does not exists");
		}
		handle = handleParam.getValue();
		logger.info("Handle " + fullname + " has id: " + handle);
		return handle;
	}

	protected float adjustValue(float value) {
		return value / MAGIC_FACTOR;
	}
}
