package ar.com.sia.algorithm.qlearning;

public interface StateUpdater {

	QAction chooseAction(QState state);

	float getReward(QState state, QState next);

}
