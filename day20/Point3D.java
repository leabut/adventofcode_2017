public class Point3D {
	long X = 0;
	long Y = 0;
	long Z = 0;
	
	public Point3D(long x, long y, long z) {
		X = x;
		Y = y;
		Z = z;
	}
	
	public Point3D() {
		return;
	}

	public long getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public long getY() {
		return Y;
	}

	public void setY(long y) {
		Y = y;
	}

	public long getZ() {
		return Z;
	}

	public void setZ(long z) {
		Z = z;
	}
	
	public void set(long x, long y, long z) {
		X = x;
		Y = y;
		Z = z;
	}
	
	public boolean equals2(Point3D point) {
		if(point.getX() == X && point.getY() == Y && point.getZ() == Z) {
			return true;
		}
		return false;
	}
}
