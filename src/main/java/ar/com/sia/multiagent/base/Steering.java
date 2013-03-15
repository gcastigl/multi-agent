package ar.com.sia.multiagent.base;

public interface Steering {

	void setModel(AgentModel model);

	void advance();

	void rotate(float degrees);

}
