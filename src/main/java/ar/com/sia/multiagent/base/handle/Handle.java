package ar.com.sia.multiagent.base.handle;

import org.apache.log4j.Logger;

import vrep.server.FloatWA;
import vrep.server.IntW;
import vrep.server.RemoteApi;
import ar.com.sia.multiagent.base.RemoteApiClient;

public class Handle extends RemoteApiClient {

	private static final Logger logger = Logger.getLogger(Handle.class);

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
		getRemoteApi().simxGetObjectPosition(getHandle(), -1, position, API_OP_MODE);
		return position.getArray();
	}

	public void setAbsolutePosition(float[] position) {
		getRemoteApi().simxSetObjectPosition(getHandle(), -1, new FloatWA(position), API_OP_MODE);
	}
	
	public int fetch(String agentName) {
		IntW handleParam = new IntW(0);
		String fullname = agentName + "_" + getName();
		int result = getRemoteApi().simxGetObjectHandle(fullname, handleParam, API_OP_MODE);
		if (result != RemoteApi.simx_error_noerror) {
			throw new IllegalStateException("Object handle for " + fullname + " does not exists");
		}
		handle = handleParam.getValue();
		logger.info("Handle " + fullname + " has id: " + handle);
		return handle;
	}

}
