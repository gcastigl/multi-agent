package ar.com.sia.util;

public class MathUtil {

	public static boolean equals(float v1, float v2) {
		if (Math.abs(v1 - v2) < 0.0001f) {
			return true;
		}
		return false;
	}

}
