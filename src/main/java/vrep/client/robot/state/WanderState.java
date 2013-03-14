package vrep.client.robot.state;

import vrep.client.robot.Robot;
import vrep.client.robot.RobotState;

public class WanderState extends RobotState {

	private final static float STEP = 0.05f;
	private float t;
	private float radius;
	
	public WanderState(Robot robot) {
		super(robot);
	}
	
	@Override
	public void enter() {
		t = 0;
		radius = 2;
	}
	
	@Override
	public void update(long elapsedTime) {
		t += STEP;
		float[] position = new float[3];
		position[0] = (float) Math.sin(t) * radius;
		position[1] = (float) Math.cos(t) * radius;
		position[2] = 0.25f;
		robot.setAbsolutePosition(position);
	}

}
