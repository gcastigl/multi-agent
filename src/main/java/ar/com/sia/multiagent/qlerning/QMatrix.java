package ar.com.sia.multiagent.qlerning;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class QMatrix {

	private Map<QState, Map<QAction, Float>> values;
	private Collection<QAction> allActions;
	private float defaultvalue;

	public QMatrix(Collection<QState> states, Collection<QAction> actions) {
		this.allActions = actions;
		defaultvalue = 0;
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
			actions = new HashMap<QAction, Float>();
			actions.put(action, defaultvalue);
			values.put(state, actions);
		}
		return actions.get(action);
	}

	public void set(QState state, QAction action, float value) {
		Map<QAction, Float> actions = values.get(state);
		if (actions == null) {
			actions = new HashMap<QAction, Float>();
			values.put(state, actions);
		}
		actions.put(action, value);
	}
}
