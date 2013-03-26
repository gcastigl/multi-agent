package ar.com.sia.multiagent.base;

import vrep.server.RemoteApi;

public abstract class RemoteApiClient {

	protected static final int MODE_NON_BLOCKING = RemoteApi.simx_opmode_oneshot;
	protected static final int MODE_BLOCKING = RemoteApi.simx_opmode_oneshot_wait;
	
	protected RemoteApi getRemoteApi() {
		return SimulationServer.getInstance().getRemoteApi();
	}

}
