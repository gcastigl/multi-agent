package ar.com.sia.multiagent.impl.train.qlearning.state;

public enum Direction {
	FRONT {
		@Override
		public Direction rotateRight() {
			return RIGHT;
		}

		@Override
		public Direction rotateLeft() {
			return LEFT;
		}
	},
	BACK {
		@Override
		public Direction rotateRight() {
			return LEFT;
		}

		@Override
		public Direction rotateLeft() {
			return RIGHT;
		}
	},
	RIGHT {
		@Override
		public Direction rotateRight() {
			return BACK;
		}

		@Override
		public Direction rotateLeft() {
			return FRONT;
		}
	},
	LEFT {
		@Override
		public Direction rotateRight() {
			return FRONT;
		}

		@Override
		public Direction rotateLeft() {
			return BACK;
		}
	};

	public abstract Direction rotateRight();

	public abstract Direction rotateLeft();

}
