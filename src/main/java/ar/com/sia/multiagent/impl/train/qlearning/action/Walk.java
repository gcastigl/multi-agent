package ar.com.sia.multiagent.impl.train.qlearning.action;

import ar.com.sia.algorithm.qlearning.QAction;
import ar.com.sia.multiagent.impl.train.qlearning.state.Direction;
import ar.com.sia.util.MathUtil;

public class Walk implements QAction {

	private float distance;
	private Direction direction;

	public Walk(Direction direction, float distance) {
		this.distance = distance;
		this.direction = direction;
	}

	public float getDistance() {
		return distance;
	}

	public Direction getDirection() {
		return direction;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Walk)) {
			return false;
		}
		return MathUtil.equals(distance, ((Walk) obj).distance);
	}

	@Override
	public int hashCode() {
		return Float.valueOf(distance).hashCode();
	}

	@Override
	public String toString() {
		return "Walk " + direction;
	}
}
