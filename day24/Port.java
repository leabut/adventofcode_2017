public class Port {
	public int sideA;
	public int sideB;
	
	public Port(int sideA, int sideB) {
		this.sideA = sideA;
		this.sideB = sideB;
	}
	
	public boolean match(Port p) {
		if(p.sideA == this.sideA || p.sideB == this.sideB || p.sideB == this.sideA || p.sideA == this.sideB) {
			return true;
		}
		return false;
	}
	
	public boolean equals2(Port p) {
		if(this.sideA == p.sideA && this.sideB == p.sideB) {
			return true;
		}
		
		return false;
	}
	
	public int stability() {
		return sideA + sideB;
	}
}
