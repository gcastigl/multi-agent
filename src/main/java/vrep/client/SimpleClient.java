package vrep.client;

import vrep.server.IntWA;
import vrep.server.RemoteApi;

@SuppressWarnings("static-access")
public class SimpleClient {
	
	public static void main(String[] args) {
		System.out.println("Program started");
		RemoteApi vrep = new RemoteApi();
		if (vrep.simxStart("127.0.0.1", 19998, true, true, 5000) != -1) {
			System.out.println("Connected to remote API server");
			IntWA objectHandles = new IntWA(1);
			int ret = vrep.simxGetObjects(vrep.sim_handle_all, objectHandles, vrep.simx_opmode_oneshot_wait);
			if (ret == vrep.simx_error_noerror) {
				System.out.format("Number of objects in the scene: %d\n", objectHandles.getArray().length);
			} else {
				System.out.format("Remote API function call returned with error code: %d\n", ret);
			}
			vrep.simxFinish();
		} else {
			System.out.println("Failed connecting to remote API server");
		}
		System.out.println("Program ended");
	}

}
