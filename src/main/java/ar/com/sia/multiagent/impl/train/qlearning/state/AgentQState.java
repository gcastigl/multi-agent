package ar.com.sia.multiagent.impl.train.qlearning.state;

import ar.com.sia.algorithm.qlearning.QAction;
import ar.com.sia.algorithm.qlearning.QState;
import ar.com.sia.multiagent.impl.train.qlearning.action.MoveForward;
import ar.com.sia.multiagent.impl.train.qlearning.action.RotateAction;

public class AgentQState implements QState {
	
	private int row, column;
	private Direction direction;

	public AgentQState(int row, int column, Direction direction) {
		this.row = row;
		this.column = column;
		this.direction = direction;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Direction getDirection() {
		return direction;
	}

	@Override
	public QState apply(QAction action) {
		int newRow = row;
		int newColumn = column;
		Direction newDirection = direction;
		if (action instanceof RotateAction) {
			newDirection = ((RotateAction) action).isRight() ? direction.rotateRight() : direction.rotateLeft();
		} else if (action instanceof MoveForward) {
			switch (direction) {
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
					throw new IllegalStateException(direction.toString());
			}
		} else {
			throw new IllegalStateException(action.getClass().toString());
		}
		return new AgentQState(newRow, newColumn, newDirection);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof AgentQState)) {
			return false;
		}
		AgentQState other = (AgentQState) obj; 
		return row == other.getRow() && column == other.getColumn() && direction.equals(other.getDirection());
	}
	
	@Override
	public int hashCode() {
		return Integer.valueOf(row + 101 * column).hashCode() + 31 * direction.hashCode();
	}
	
	@Override
	public String toString() {
		return "row: " + row + " | column: " + column + " | direction: " + direction;
	}
}
