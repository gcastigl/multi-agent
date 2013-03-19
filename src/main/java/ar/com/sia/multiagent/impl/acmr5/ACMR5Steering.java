package ar.com.sia.multiagent.impl.acmr5;

import ar.com.sia.multiagent.base.AgentModel;
import ar.com.sia.multiagent.base.Steering;
import ar.com.sia.multiagent.base.handle.Joint;

public class ACMR5Steering implements Steering {

	private ACMR5AgentModel model;

	public void setModel(AgentModel model) {
		this.model = (ACMR5AgentModel) model;
	}

	public void advance() {
		Joint mainJoint = model.getJoint("vJoint");
		float position = mainJoint.getPosition();
		float maxJointPosition = mainJoint.getMaxTargetPosition();
		if (position > maxJointPosition || position < -maxJointPosition) {
			mainJoint.setTargetPosition(-position);
		}
	}

	public void rotate(float degrees) {

	}

}
