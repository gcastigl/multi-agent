package ar.com.sia.multiagent.base;

import ar.com.sia.multiagent.base.api.Action;
import ar.com.sia.multiagent.base.api.AgentState;
import ar.com.sia.multiagent.base.api.Perception;

public class PerceptionState implements AgentState {

	private Perception perception;

	public PerceptionState(Perception perception) {
		this.perception = perception;
	}

	@Override
	public AgentState apply(Action action) {
		return null;
	}

	public Perception getPerception() {
		return perception;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof PerceptionState)) {
			return false;
		}
		PerceptionState other = (PerceptionState) obj;
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String toString() {
		return ""; // "row: " + row + " | column: " + column;
	}
}
