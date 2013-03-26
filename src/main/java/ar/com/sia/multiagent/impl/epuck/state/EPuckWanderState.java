package ar.com.sia.multiagent.impl.epuck.state;

import ar.com.sia.multiagent.base.AgentState;
import ar.com.sia.multiagent.base.handle.ProximitySensor;
import ar.com.sia.multiagent.impl.epuck.EPuckModel;
import ar.com.sia.multiagent.impl.epuck.EPuckModel.Sensor;

public class EPuckWanderState extends AgentState {

	private EPuckModel agentModel;
	
	@Override
	public void enter() {
		agentModel = (EPuckModel) agent.getModel();
	}
	
	@Override
	public void update(long elapsedTime) {
		ProximitySensor frontLeftSensor = agentModel.getProximitySensor(Sensor.FRONT_LEFT);
		ProximitySensor frontRightSensor = agentModel.getProximitySensor(Sensor.FRONT_RIGHT);
		if (frontLeftSensor.sensingObstacle() || frontRightSensor.sensingObstacle()) {
			ProximitySensor rightSensor = agentModel.getProximitySensor(Sensor.RIGHT);
			ProximitySensor leftSensor = agentModel.getProximitySensor(Sensor.LEFT);
			if (!leftSensor.sensingObstacle()) {
				agentModel.getSteering().rotate(90f);
			} else if (!rightSensor.sensingObstacle()) {
				agentModel.getSteering().rotate(-90f);
			}
		} else {
			agent.getModel().getSteering().advance();
		}
	}
}
