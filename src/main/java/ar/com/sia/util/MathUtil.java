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
		int index = (int) Math.random() * collection.size();
		return new LinkedList<T>(collection).get(index);
	}

}
