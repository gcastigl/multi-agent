package ar.com.sia.algorithm.qlearning;

public class QLearningMatrixUpdater {

	private float alpha, gamma;
	private QMatrix values;

	public QLearningMatrixUpdater(float alpha, float gamma, QMatrix values) {
		this.alpha = alpha;
		this.gamma = gamma;
		this.values = values;
	}

	public void updateValue(QState state, QAction action, float reinforcement) {
		float prevValue = values.get(state, action);
		float updatedVAlue = prevValue + alpha * (reinforcement + gamma * maxValue(state) - prevValue);
		values.set(state, action, updatedVAlue);
	}
	
	public float maxValue(QState state) {
		Float max = null;
		for (QAction action : values.getActions()) {
			float value = values.get(state, action);
			if (max == null || value > max) {
				max = value;
			}
		}
		return max;
	}
	
}
