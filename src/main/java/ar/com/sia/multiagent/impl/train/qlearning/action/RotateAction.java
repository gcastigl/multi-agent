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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof RotateAction)) {
			return false;
		}
		return right == ((RotateAction) obj).right;
	}
	
	@Override
	public int hashCode() {
		return Boolean.valueOf(right).hashCode();
	}
	
	@Override
	public String toString() {
		return "Rotate: " + (right ? "right" : "left");
	}
}
