package ar.com.sia.multiagent.impl.train.qlearning.state;

import ar.com.sia.algorithm.qlearning.QAction;
import ar.com.sia.algorithm.qlearning.QState;
import ar.com.sia.multiagent.base.Agent;
import ar.com.sia.multiagent.impl.train.qlearning.action.MoveForward;
import ar.com.sia.multiagent.impl.train.qlearning.action.RotateAction;

public class AgentQState implements QState {
	
	private int row, column;
	private Direction direction;

	public AgentQState(int row, int column, Direction direction) {
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
		if (action instanceof RotateAction) {
			RotateAction rotateAction = (RotateAction) action;
			Direction dir = rotateAction.isRight() ? direction.rotateRight() : direction.rotateLeft();
			return new AgentQState(row, column, dir);
		} else if (action instanceof MoveForward) {
			switch (direction) {
				int newRow = row;
				int newColumn = column;
				FRONT:
					newRow++;
					break;
				RIGHT:
					newColumn++;
					break;
				LEFT:
					newColumn--;
					break;
				BACK:
					newRow--;	
			}
		}
	}
}
