package ar.com.sia.multiagent.base.handle;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import vrep.server.FloatWA;
import vrep.server.IntW;
import vrep.server.RemoteApi;
import ar.com.sia.multiagent.base.RemoteApiClient;
import ar.com.sia.util.StringUtil;

public class Handle extends RemoteApiClient {

	private static final Logger logger = Logger.getLogger(Handle.class);

	private Integer handle;
	private String name;
	private List<Handle> childs;
	
	public Handle(String name) {
		childs = new LinkedList<Handle>();
		this.name = name;
		handle = null;
	}

	public int getHandle() {
		return handle;
	}

	public String getName() {
		return name;
	}
	
	public void addChild(Handle handle) {
		childs.add(handle);
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

	public void addOrientationRecursive(float alpha, float beta, float gamma) {
		addOrientation(alpha, beta, gamma);
		for (Handle child : childs) {
			child.addOrientationRecursive(alpha, beta, gamma);
		}
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
		getRemoteApi().simxSetObjectOrientation(getHandle(), RemoteApi.sim_handle_parent, eulerAngles, MODE_BLOCKING);
	}

	public float[] getOrientation() {
		FloatWA eulerAngles = new FloatWA(new float[] {});
		getRemoteApi().simxGetObjectOrientation(getHandle(), RemoteApi.sim_handle_parent, eulerAngles, MODE_BLOCKING);
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
		for (Handle child : childs) {
			child.fetch(agentName);
		}
		return handle;
	}

	public int getChildHandle(int index) {
		IntW childObjectHandle = new IntW(0);
		getRemoteApi().simxGetObjectChild(getHandle(), index, childObjectHandle, MODE_BLOCKING);
		return childObjectHandle.getValue();
	}

	public Handle getChild(String name) {
		if (StringUtil.equals(this.name, name)) {
			return this;
		}
		for (Handle child : childs) {
			Handle handle = child.getChild(name);
			if (handle != null) {
				return handle;
			}
		}
		return null;
	}
}
