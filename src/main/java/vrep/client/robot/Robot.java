package vrep.client.robot;

import org.apache.log4j.Logger;

import vrep.client.robot.state.WanderState;
import vrep.server.FloatWA;
import vrep.server.IntW;
import vrep.server.RemoteApi;

public class Robot {

	private static final Logger logger = Logger.getLogger(Robot.class);
	private static final int API_OP_MODE = RemoteApi.simx_opmode_oneshot_wait;
	
	private RemoteApi remoteApi;
	private String name;
	private int handle;
	private RobotState state;
	
	public Robot(RemoteApi remoteApi, String name) {
		this.remoteApi = remoteApi;
		this.name = name;
		fetchHandle();
		state = new WanderState(this);
		state.enter();
	}

	private void fetchHandle() {
		IntW handleParam = new IntW(0);
		int result = remoteApi.simxGetObjectHandle(name, handleParam, API_OP_MODE);
		if (result != RemoteApi.simx_error_noerror) {
			throw new IllegalStateException("Robot " + name + " does not exists");
		}
		this.handle = handleParam.getValue();
		logger.info("Robot " + name + " has handle: " + handle);
	}
	
	public void update(long elapsedTime) {
		state.update(elapsedTime);
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
