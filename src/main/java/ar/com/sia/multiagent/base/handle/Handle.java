package ar.com.sia.multiagent.base.handle;

import org.apache.log4j.Logger;

import ar.com.sia.multiagent.base.RemoteApiClient;

import vrep.server.FloatWA;
import vrep.server.IntW;
import vrep.server.RemoteApi;

public class Handle extends RemoteApiClient {

	private static final Logger logger = Logger.getLogger(Handle.class);
	
	private int handle;
	private String robotName, name;
	
	public Handle(RemoteApi remoteApi, String agentName, String name) {
		super(remoteApi);
		this.robotName = agentName;
		this.name =  name;
		fetchHandle();
	}
	
	protected void fetchHandle() {
		IntW handleParam = new IntW(0);
		int result = remoteApi.simxGetObjectHandle(getFullName(), handleParam, API_OP_MODE);
		if (result != RemoteApi.simx_error_noerror) {
			throw new IllegalStateException("Object handle for " + getFullName() + " does not exists");
		}
		this.handle = handleParam.getValue();
		logger.info("Robot " + getFullName() + " has handle: " + handle);
	}
	
	public String getName() {
		return name;
	}
	
	public String getFullName() {
		return robotName + "_" + name;
	}
	
	public int getHandle() {
		return handle;
	}
	
	public float[] getAbsolutePosition() {
		FloatWA position = new FloatWA(0);
		remoteApi.simxGetObjectPosition(handle, -1, position, API_OP_MODE);
		return position.getArray();
	}
	
	public void setAbsolutePosition(float[] position) {
		remoteApi.simxSetObjectPosition(handle, -1, new FloatWA(position), API_OP_MODE);
	}
}
