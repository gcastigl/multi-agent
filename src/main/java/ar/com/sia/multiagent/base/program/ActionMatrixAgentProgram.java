package ar.com.sia.multiagent.base.program;

import ar.com.sia.multiagent.base.ActionMatrix;
import ar.com.sia.multiagent.base.PerceptionState;
import ar.com.sia.multiagent.base.api.Action;
import ar.com.sia.multiagent.base.api.AgentState;

public class ActionMatrixAgentProgram extends AgentProgram {

	private ActionMatrix actionMatrix;

	public ActionMatrixAgentProgram(ActionMatrix actionMatrix) {
		this.actionMatrix = actionMatrix;
	}

	@Override
	protected void start() {
		AgentState current = getCurrentState();
		Action action = actionMatrix.getAction(current);
		setCurrentState(current.apply(action));
	}

	private AgentState getCurrentState() {
		return new PerceptionState(agent.sense());
	}

	private void setCurrentState(AgentState state) {
		// PerceptionState agentState = (PerceptionState) state;
		// float[] position = new float[] {agentState.getRow(), agentState.getColumn(), 0f};
		// agent.getModel().setPosition(position);
	}
}
