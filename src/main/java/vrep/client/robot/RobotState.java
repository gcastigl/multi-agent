package vrep.client.robot;

public abstract class RobotState {

	protected Robot robot;
	
	public RobotState(Robot robot) {
		this.robot = robot;
	}
	
	public void enter() {
	}

	public abstract void update(long elapsedTime);

	public void exit() {
	}

}
