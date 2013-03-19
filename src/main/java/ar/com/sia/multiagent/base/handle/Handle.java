package ar.com.sia.multiagent.base.handle;

import org.apache.log4j.Logger;

import ar.com.sia.multiagent.base.RemoteApiClient;

import vrep.server.FloatWA;
import vrep.server.IntW;
import vrep.server.RemoteApi;

public class Handle extends RemoteApiClient {

	private static final Logger logger = Logger.getLogger(Handle.class);

	private int handle;
	private String agentName, simpleName;

	public Handle(RemoteApi remoteApi, String agentName, String simpleName) {
		super(remoteApi);
		this.agentName = agentName;
		this.simpleName = simpleName;
		this.handle = fetchHandle();
	}

	private int fetchHandle() {
		IntW handleParam = new IntW(0);
		int result = remoteApi.simxGetObjectHandle(getFullName(), handleParam, API_OP_MODE);
		if (result != RemoteApi.simx_error_noerror) {
			throw new IllegalStateException("Object handle for " + getFullName() + " does not exists");
		}
		int handle = handleParam.getValue();
		logger.info("Handle " + getFullName() + " has id: " + handle);
		return handle;
	}

	public int getHandle() {
		return handle;
	}

	public String getFullName() {
		return agentName + "_" + simpleName;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public String getAgentName() {
		return agentName;
	}
	
	public float[] getAbsolutePosition() {
		FloatWA position = new FloatWA(0);
		remoteApi.simxGetObjectPosition(getHandle(), -1, position, API_OP_MODE);
		return position.getArray();
	}

	public void setAbsolutePosition(float[] position) {
		remoteApi.simxSetObjectPosition(getHandle(), -1, new FloatWA(position), API_OP_MODE);
	}
}
