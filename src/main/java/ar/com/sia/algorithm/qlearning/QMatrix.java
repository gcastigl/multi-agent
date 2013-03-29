package ar.com.sia.algorithm.qlearning;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ar.com.sia.util.MathUtil;

public class QMatrix {

	private Map<QState, Map<QAction, Float>> values;
	private Collection<QAction> allActions;
	private float defaultvalue;

	public QMatrix(Collection<QState> states, Collection<QAction> actions) {
		this.allActions = actions;
		defaultvalue = 0;
		values = new HashMap<QState, Map<QAction, Float>>();
		for (QState state : states) {
			for (QAction action : actions) {
				set(state, action, 0);
			}
		}
	}

	public Collection<QAction> getActions() {
		return allActions;
	}

	public float get(QState state, QAction action) {
		Map<QAction, Float> actions = values.get(state);
		if (actions == null) {
			return defaultvalue;
		}
		Float value = actions.get(action); 
		return value == null ? defaultvalue : value;
	}

	public void set(QState state, QAction action, float value) {
		if (MathUtil.equals(defaultvalue, value)) {
			return;
		}
		Map<QAction, Float> actions = values.get(state);
		if (actions == null) {
			actions = new HashMap<QAction, Float>();
			values.put(state, actions);
		}
		actions.put(action, value);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int size = 0;
		builder.append("Default value: " + defaultvalue + "\n{\n");
		for (Entry<QState, Map<QAction, Float>> stateEntry : values.entrySet()) {
			for (Entry<QAction, Float> actionEntry : stateEntry.getValue().entrySet()) {
				size++;
				builder.append("\t[" + stateEntry.getKey() + ", " + actionEntry.getKey() + "] = " + actionEntry.getValue() + "\n");
			}
		}
		builder.append("\n}\nSize: " + size);
		builder.append("\n\n");
		return builder.toString();
	}
}
