package ar.com.sia.multiagent.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ar.com.sia.multiagent.base.api.Action;
import ar.com.sia.multiagent.base.api.AgentState;
import ar.com.sia.qlearning.QMatrix;

public class ActionMatrix {

	private Map<AgentState, Action> actionMatrix;

	public ActionMatrix(QMatrix trainMatrix) {
		actionMatrix = new HashMap<AgentState, Action>();
		for (AgentState state : trainMatrix.getQStateEntries()) {
			actionMatrix.put(state, trainMatrix.getBestAction(state));
		}
	}

	public Action getAction(AgentState state) {
		return actionMatrix.get(state);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("{\n");
		for (Entry<AgentState, Action> entry : actionMatrix.entrySet()) {
			builder.append("\t" + entry.getKey() + " => " + entry.getValue() + "\n");
		}
		builder.append("}\n");
		return builder.toString();
	}
}
