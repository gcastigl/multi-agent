package ar.com.sia.multiagent.impl.train.qlearning.action;

import ar.com.sia.algorithm.qlearning.QAction;

public class MoveForward implements QAction {

	public float distance;

	public MoveForward(float distance) {
		this.distance = distance;
	}

	public float getDistance() {
		return distance;
	}
}
