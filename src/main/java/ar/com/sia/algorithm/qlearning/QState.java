package ar.com.sia.algorithm.qlearning;

public interface QState {

	QState apply(QAction action) throws NotApplicableException;

}
