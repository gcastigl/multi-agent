package ar.com.sia.multiagent.impl.cuboid;

import ar.com.sia.multiagent.base.Steering;

public class CuboidSteering extends Steering<CuboidModel> {

	public CuboidSteering(CuboidModel model) {
		super(model);
	}

	@Override
	public void advance() {
		float[] position = getModel().getPosition();
		float[] rotation = getModel().getRotation();
		float gamma = rotation[2];
		float x = (float) (rotation[0] * Math.cos(gamma) - rotation[1] * Math.sin(gamma));
		float y = (float) (rotation[0] * Math.sin(gamma) + rotation[1] * Math.sin(gamma));
		getModel().addPosition(x, y, position[2]);
	}

	@Override
	public void rotate(float alpha, float beta, float gamma) {
		getModel().addRotation(alpha, beta, gamma);
	}

}
