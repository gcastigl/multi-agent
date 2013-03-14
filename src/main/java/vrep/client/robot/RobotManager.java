package vrep.client.robot;

import java.util.LinkedList;
import java.util.List;

import vrep.server.RemoteApi;

public class RobotManager {

	private List<Robot> activeRobots;
	private boolean finished;
	
	public RobotManager(RemoteApi remoteApi) {
		activeRobots = new LinkedList<Robot>();
		activeRobots.add(new Robot(remoteApi, "Sphere"));
	}
	
	public void run() {
		long startTime, elapsedTime = 0;
		while (!finished) {
			startTime = System.currentTimeMillis();
			for (Robot robot : activeRobots) {
				robot.update(elapsedTime);
			}
			sleep(50);
			elapsedTime = System.currentTimeMillis() - startTime;
		}
	}
	
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setFinished() {
		this.finished = true;
	}
	
	public boolean isFinished() {
		return finished;
	}
}
