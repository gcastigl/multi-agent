package ar.com.sia.qlearning;

import ar.com.sia.multiagent.base.api.Action;
import ar.com.sia.multiagent.base.api.AgentState;


public class QLearningMatrixUpdater {

	private float alpha, gamma;
	private QMatrix values;

	public QLearningMatrixUpdater(float alpha, float gamma, QMatrix values) {
		this.alpha = alpha;
		this.gamma = gamma;
		this.values = values;
	}

	public void updateValue(AgentState state, Action action, float reinforcement) {
		float prevValue = values.get(state, action);
		values.getBestAction(state);
		float updatedVAlue = prevValue + alpha * (reinforcement + gamma * maxValue(state) - prevValue);
		values.set(state, action, updatedVAlue);
	}

	public float maxValue(AgentState state) {
		return values.getBestActionValue(state);
	}

	public QMatrix getMatrix() {
		return values;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("alpha: " + alpha + " | gamma: " + gamma + "\n");
		builder.append(values.toString());
		return builder.toString();
	}
}
