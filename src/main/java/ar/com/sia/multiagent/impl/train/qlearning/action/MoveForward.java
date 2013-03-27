package ar.com.sia.multiagent.impl.train.qlearning.action;

import ar.com.sia.algorithm.qlearning.QAction;
import ar.com.sia.util.MathUtil;

public class MoveForward implements QAction {

	public float distance;

	public MoveForward(float distance) {
		this.distance = distance;
	}

	public float getDistance() {
		return distance;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof MoveForward)) {
			return false;
		}
		return MathUtil.equals(distance, ((MoveForward) obj).distance);
	}
	
	@Override
	public int hashCode() {
		return Float.valueOf(distance).hashCode();
	}
	
	@Override
	public String toString() {
		return "Move forward: " + distance;
	}
}
