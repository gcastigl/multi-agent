package ar.com.sia.util;

import java.util.Collection;
import java.util.LinkedList;

public class MathUtil {

	public static boolean equals(float v1, float v2) {
		if (Math.abs(v1 - v2) < 0.0001f) {
			return true;
		}
		return false;
	}

	public static <T> T random(Collection<T> collection) {
		int index = (int) (Math.random() * collection.size());
		return new LinkedList<T>(collection).get(index);
	}
	
	public static float toRadians(float degree) {
		return degree / 57.29f;
	}
	
	protected void toRadians(float[] degrees) {
		degrees[0] = toRadians(degrees[0]);
		degrees[1] = toRadians(degrees[1]);
		degrees[2] = toRadians(degrees[2]);
	}
	
	public static float module(float[] v1) {
		float x = v1[0];
		float y = v1[1];
		float z = v1[2];
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
}
