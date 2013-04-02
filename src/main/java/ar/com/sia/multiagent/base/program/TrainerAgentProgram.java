package ar.com.sia.multiagent.base.program;

import org.apache.log4j.Logger;

import ar.com.sia.multiagent.base.api.Action;
import ar.com.sia.multiagent.base.api.AgentState;

public abstract class TrainerAgentProgram<T extends AgentState> extends AgentProgram {

	private Logger logger = Logger.getLogger(TrainerAgentProgram.class);

	private int episodes;
	private int iterations;

	public TrainerAgentProgram(int episodes, int iterations) {
		this.episodes = episodes;
		this.iterations = iterations;
	}

	@Override
	protected void start() {
		for (int episode = 0; episodes == -1 || episode < episodes; episode++) {
			setupEpisode();
			for (int iteration = 0; iteration < iterations; iteration++) {
				logger.info("===============| episode: " + (episode + 1) + " / iteration: " + (iteration + 1) + " |===================");
				T currentState = getCurrentState();
				logger.info("Current state: " + currentState);
				Action action = deliverate(currentState);
				logger.info("Action: " + action);
				agent.execute(action);
				T nextState = getCurrentState();
				logger.info("New state: " + nextState);
				updateTraining(currentState, nextState, action);
				if (isEndOfEpisode(nextState)) {
					logger.info("End of iteration: " + nextState);
					break;
				}
			}
		}
	}

	protected abstract void setupEpisode();

	protected abstract T getCurrentState();

	protected abstract Action deliverate(T state);

	protected abstract void updateTraining(T previous, T current, Action action);

	protected abstract boolean isEndOfEpisode(T state);

}
