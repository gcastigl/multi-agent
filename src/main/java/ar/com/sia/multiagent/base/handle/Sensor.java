package ar.com.sia.multiagent.base.handle;

import vrep.server.RemoteApi;

public abstract class Sensor extends Handle {

	public Sensor(RemoteApi remoteApi, String agentName, String name) {
		super(remoteApi, agentName, name);
	}

}
