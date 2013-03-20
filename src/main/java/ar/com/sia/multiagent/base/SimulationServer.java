package ar.com.sia.multiagent.base;

import org.apache.log4j.Logger;

import vrep.server.IntWA;
import vrep.server.RemoteApi;

public class SimulationServer {

	private static final Logger logger = Logger.getLogger(SimulationServer.class);
	private static final SimulationServer instance = new SimulationServer();

	public static SimulationServer getInstance() {
		return instance;
	}

	private RemoteApi remoteApi;

	private SimulationServer() {
	}

	public RemoteApi getRemoteApi() {
		return remoteApi;
	}

	public void connect(String ip, int port) throws ConnectionException {
		remoteApi = new RemoteApi();
		int status = remoteApi.simxStart(ip, port, true, true, 5000);
		if (status == -1) {
			throw new ConnectionException();
		}
		logger.trace("Connected to remote API server");
	}

	public void disconnect() {
		remoteApi.simxFinish();
	}

	public int[] getAllObjectsHandle() {
		IntWA objectHandles = new IntWA(1);
		remoteApi.simxGetObjects(RemoteApi.sim_handle_all, objectHandles, RemoteApi.simx_opmode_oneshot_wait);
		return objectHandles.getArray();
	}
}
