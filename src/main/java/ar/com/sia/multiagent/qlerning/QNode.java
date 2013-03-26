package ar.com.sia.multiagent.qlerning;

public class QNode {

	private QState state;
	private QAction action;

	public QNode(QState state, QAction action) {
		this.state = state;
		this.action = action;
	}

	public QState getState() {
		return state;
	}

	public QAction getAction() {
		return action;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof QNode)) {
			return false;
		}
		QNode other = (QNode) obj;
		return state.equals(other.getState()) && action.equals(other.action);
	}

	@Override
	public int hashCode() {
		return state.hashCode() + 101 * action.hashCode();
	}
}
