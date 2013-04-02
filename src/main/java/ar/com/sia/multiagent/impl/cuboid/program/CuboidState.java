package ar.com.sia.multiagent.impl.cuboid.program;

import ar.com.sia.multiagent.base.api.Action;
import ar.com.sia.multiagent.base.api.AgentState;
import ar.com.sia.multiagent.impl.cuboid.CuboidPerception;

public class CuboidState implements AgentState {

	private CuboidPerception perception;

	public CuboidState(CuboidPerception perception) {
		this.perception = perception;
	}

	@Override
	public AgentState apply(Action action) {
		return null;
	}

	public CuboidPerception getPerception() {
		return perception;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CuboidState)) {
			return false;
		}
		CuboidState other = (CuboidState) obj;
		return perception.equals(other.getPerception());
	}

	@Override
	public int hashCode() {
		return perception.hashCode();
	}

	@Override
	public String toString() {
		return "Percepcion {" + perception.toString() + "}";
	}
}
