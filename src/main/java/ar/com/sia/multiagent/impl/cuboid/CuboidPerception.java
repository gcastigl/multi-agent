package ar.com.sia.multiagent.impl.cuboid;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ar.com.sia.multiagent.base.api.Perception;

public class CuboidPerception implements Perception {

	private BigDecimal distanceToObject;

	public CuboidPerception() {
		distanceToObject = null;
	}

	public CuboidPerception(float distanceToObject) {
		this.distanceToObject = new BigDecimal(distanceToObject).setScale(2, RoundingMode.DOWN);
	}

	public boolean isSensing() {
		return distanceToObject != null;
	}
	
	public float getDistanceToObject() {
		return distanceToObject.floatValue();
	}
	
	@Override
	public String toString() {
		return "Perceiving object at distance: " + distanceToObject;
	}
	
	@Override
	public int hashCode() {
		return distanceToObject == null ? 0 : distanceToObject.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CuboidPerception)) {
			return false;
		}
		CuboidPerception other = (CuboidPerception) obj;
		if (distanceToObject == null) {
			return other.distanceToObject == null;
		}
		return distanceToObject.equals(other.distanceToObject);
	}
}
