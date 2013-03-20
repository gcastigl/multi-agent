package ar.com.sia.multiagent.base;

import vrep.server.RemoteApi;

public abstract class RemoteApiClient {

	protected static final int API_OP_MODE = RemoteApi.simx_opmode_oneshot_wait;

	public RemoteApi getRemoteApi() {
		return SimulationServer.getInstance().getRemoteApi();
	}

}
