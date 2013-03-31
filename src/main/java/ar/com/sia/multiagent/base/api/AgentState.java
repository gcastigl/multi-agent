package ar.com.sia.multiagent.base.api;

public interface AgentState {

	AgentState apply(Action action);

}
