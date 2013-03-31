package ar.com.sia.qlearning;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ar.com.sia.multiagent.base.api.Action;
import ar.com.sia.multiagent.base.api.AgentState;
import ar.com.sia.util.MathUtil;

public class QMatrix {

	private Map<AgentState, Map<Action, Float>> values;
	private Collection<Action> allActions;
	private float defaultvalue;

	public QMatrix(Collection<Action> allActions) {
		this(allActions, 0f);
	}

	public QMatrix(Collection<Action> allActions, float defaultValue) {
		this.allActions = allActions;
		this.defaultvalue = defaultValue;
		values = new HashMap<AgentState, Map<Action, Float>>();
	}

	public Set<AgentState> getQStateEntries() {
		return values.keySet();
	}
	
	public Collection<Action> getAllActions() {
		return allActions;
	}
	
	public Action getBestAction(AgentState state) {
		Action best = null;
		float bestActionValue = 0f;
		for (Action action : allActions) {
			float value = get(state, action);
			if (best == null || value > bestActionValue) {
				best = action;
				bestActionValue = value;
			}
		}
		return best;
	}
	
	public float getBestActionValue(AgentState state) {
		Float bestActionValue = null;
		for (Action action : allActions) {
			float value = get(state, action);
			if (bestActionValue == null || value > bestActionValue) {
				bestActionValue = value;
			}
		}
		return bestActionValue;
	}
	
	public float get(AgentState state, Action action) {
		Map<Action, Float> actions = values.get(state);
		if (actions == null) {
			return defaultvalue;
		}
		Float value = actions.get(action); 
		return value == null ? defaultvalue : value;
	}

	public void set(AgentState state, Action action, float value) {
		if (MathUtil.equals(defaultvalue, value)) {
			return;
		}
		Map<Action, Float> actions = values.get(state);
		if (actions == null) {
			actions = new HashMap<Action, Float>();
			values.put(state, actions);
		}
		actions.put(action, value);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int size = 0;
		builder.append("Default value: " + defaultvalue + "\n{\n");
		for (Entry<AgentState, Map<Action, Float>> stateEntry : values.entrySet()) {
			for (Entry<Action, Float> actionEntry : stateEntry.getValue().entrySet()) {
				size++;
				builder.append("\t[" + stateEntry.getKey() + ", " + actionEntry.getKey() + "] = " + actionEntry.getValue() + "\n");
			}
		}
		builder.append("\n}\nSize: " + size);
		builder.append("\n\n");
		return builder.toString();
	}
}
