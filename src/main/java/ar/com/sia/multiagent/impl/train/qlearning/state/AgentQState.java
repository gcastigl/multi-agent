package ar.com.sia.multiagent.impl.train.qlearning.state;

import ar.com.sia.algorithm.qlearning.QAction;
import ar.com.sia.algorithm.qlearning.QState;
import ar.com.sia.multiagent.impl.train.qlearning.action.Walk;

public class AgentQState implements QState {

	private int row, column;

	public AgentQState(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	@Override
	public QState apply(QAction action) {
		Walk walkAction = (Walk) action;
		int newRow = row;
		int newColumn = column;
		switch (walkAction.getDirection()) {
		case FRONT:
			newRow++;
			break;
		case RIGHT:
			newColumn++;
			break;
		case LEFT:
			newColumn--;
			break;
		case BACK:
			newRow--;
			break;
		default:
			throw new IllegalStateException(walkAction.getDirection().toString());
		}
		return new AgentQState(newRow, newColumn);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof AgentQState)) {
			return false;
		}
		AgentQState other = (AgentQState) obj;
		return row == other.getRow() && column == other.getColumn();
	}

	@Override
	public int hashCode() {
		return Integer.valueOf(row + 101 * column).hashCode();
	}

	@Override
	public String toString() {
		return "row: " + row + " | column: " + column;
	}
}
