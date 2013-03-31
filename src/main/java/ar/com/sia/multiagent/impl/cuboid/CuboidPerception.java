package ar.com.sia.multiagent.impl.cuboid;

import ar.com.sia.multiagent.base.api.Perception;

public class CuboidPerception implements Perception {

	private Float distanceToObject;

	public CuboidPerception() {
		distanceToObject = null;
	}

	public CuboidPerception(float distanceToObject) {
		distanceToObject = 0f;
	}

	public float getDistanceToObject() {
		return distanceToObject;
	}
}
