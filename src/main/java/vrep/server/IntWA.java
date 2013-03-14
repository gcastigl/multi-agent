package vrep.server;

public class IntWA {
	int[] w;

	public IntWA(int i) {
		w = new int[i];
	}

	public void initArray(int i) {
		w = new int[i];
	}

	public int getLength() {
		return w.length;
	}

	public int[] getNewArray(int i) {
		w = new int[i];
		return w;
	}

	public int[] getArray() {
		return w;
	}
}
