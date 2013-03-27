package ar.com.sia.multiagent.impl.train.qlearning.action;

import ar.com.sia.algorithm.qlearning.QAction;

public class RotateAction implements QAction {

	private boolean right;

	public RotateAction(boolean right) {
		this.right = right;
	}

	public boolean isRight() {
		return right;
	}
}
