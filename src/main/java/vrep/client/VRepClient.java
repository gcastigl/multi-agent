package vrep.client;

import org.apache.log4j.Logger;

import vrep.client.robot.RobotManager;
import vrep.server.IntWA;
import vrep.server.RemoteApi;

@SuppressWarnings("static-access")
public class VRepClient {
	
	private static final Logger logger = Logger.getLogger(VRepClient.class);
	
	public static void main(String[] args) {
		logger.trace("Program started");
		RemoteApi vrep = new RemoteApi();
		if (vrep.simxStart("127.0.0.1", 19998, true, true, 5000) != -1) {
			logger.trace("Connected to remote API server");
			IntWA objectHandles = new IntWA(1);
			int ret = vrep.simxGetObjects(vrep.sim_handle_all, objectHandles, vrep.simx_opmode_oneshot_wait);
			if (ret == vrep.simx_error_noerror) {
				logger.info("Number of objects in the scene: %d" + objectHandles.getArray().length);
				new RobotManager(vrep).run();
			} else {
				logger.error("Remote API function call returned with error code: " + ret);
			}
			vrep.simxFinish();
		} else {
			logger.error("Failed connecting to remote API server");
		}
		logger.trace("Program ended");
	}

}
