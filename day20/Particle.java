public class Particle {
	
	public int id = 0;
	public Point3D velocity = null;
	public Point3D acceleration = null;
	public Point3D position = null;
	public boolean isFlagged = false;
	
	public Particle(int id, Point3D velocity, Point3D acceleration, Point3D position) {
		this.id = id;
		this.velocity = velocity;
		this.acceleration = acceleration;
		this.position = position;
	}
	
	public void update() {
		long vX = velocity.getX() + acceleration.getX();
		long vY = velocity.getY() + acceleration.getY();
		long vZ = velocity.getZ() + acceleration.getZ();
		
		velocity.set(vX, vY, vZ);
		
		long pX = position.getX() + velocity.getX();
		long pY = position.getY() + velocity.getY();
		long pZ = position.getZ() + velocity.getZ();
		
		position.set(pX, pY, pZ);
	}
	
	public long distanceFromOrigin() {
		return Math.abs(position.getX()) + Math.abs(position.getY()) + Math.abs(position.getZ());
	}
	
	public boolean equals2(Particle particle) {
		if(particle.position.equals2(position)) {
			return true;
		}
		return false;
	}
}
