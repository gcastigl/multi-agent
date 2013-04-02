package ar.com.sia.multiagent.base;

public abstract class Steering<T extends AgentModel> extends RemoteApiClient {

	private T model;

	public Steering(T model) {
		this.model = model;
	}

	public T getModel() {
		return model;
	}

	public abstract void advance();

	public abstract void rotate(float alpha, float beta, float gamma);

}
