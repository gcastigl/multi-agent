package vrep.server;

public class FloatWA {
	float[] w;

	public FloatWA(int i) {
		w = new float[i];
	}

	public void initArray(int i) {
		w = new float[i];
	}

	public float[] getArray() {
		return w;
	}

	public int getLength() {
		return w.length;
	}

	public float[] getNewArray(int i) {
		w = new float[i];
		for (int k = 0; k < i; k++)
			w[k] = 0.0f;
		return w;
	}
}
