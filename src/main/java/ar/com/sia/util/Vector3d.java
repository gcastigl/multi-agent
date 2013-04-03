package ar.com.sia.util;

public class Vector3d {

	public float x, y, z;

	public Vector3d(float[] vec) {
		this.x = vec[0];
		this.y = vec[1];
		this.z = vec[2];
	}

	public Vector3d(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float distance(float[] other) {
		float dx = x - other[0];
		float dy = y - other[1];
		float dz = z - other[2];
		return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public void normalize() {
		float module = module();
		x /= module;
		y /= module;
		z /= module;
	}

	public float module() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public Vector3d minus(Vector3d other) {
		Vector3d clone = clone();
		clone.x -= other.x;
		clone.y -= other.y;
		clone.z -= other.z;
		return clone;
	}

	public Vector3d multiply(float value) {
		Vector3d clone = clone();
		clone.x *= value;
		clone.y *= value;
		clone.z *= value;
		return clone;
	}
	
	@Override
	public Vector3d clone() {
		return new Vector3d(x, y, z);
	}
}
