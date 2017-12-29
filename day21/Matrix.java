import java.util.Vector;

public class Matrix {
	
	public Vector<Vector<Character>> data = new Vector<Vector<Character>>();
	public int dim = 0;
	
	public Matrix(String matrixString, int dim) {
		this.dim = dim;
		
		char[] tmp = matrixString.toCharArray();
		
		Vector<Character> tmpVec = new Vector<Character>();
		for(int i = 0; i < tmp.length; i++) {			
			if(i % dim == 0 && i != 0) {
				data.add(tmpVec);
				tmpVec = new Vector<Character>();
			}
			tmpVec.add(tmp[i]);
		}
		
		data.addElement(tmpVec);
	}
	
	@Override
	public boolean equals(Object o) {
		Matrix m = (Matrix) o;
		
		if(this.dim != m.dim) {
			return false;
		}
		
		for(int i = 0; i < this.dim; i++) {
			for(int k = 0; k < this.dim; k++) {
				if(m.data.get(i).get(k) != this.data.get(i).get(k)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public Matrix[] split() {
		if(dim != 4) {
			return null;
		}
		
		char[] charMatrixOne = new char[this.dim/2*this.dim/2];
		char[] charMatrixTwo = new char[this.dim/2*this.dim/2];
		char[] charMatrixThree = new char[this.dim/2*this.dim/2];
		char[] charMatrixFour = new char[this.dim/2*this.dim/2];
		
		charMatrixOne[0] = data.get(0).get(0);
		charMatrixOne[1] = data.get(0).get(1);
		charMatrixOne[2] = data.get(1).get(0);
		charMatrixOne[3] = data.get(1).get(1);
		
		charMatrixTwo[0] = data.get(0).get(2);
		charMatrixTwo[1] = data.get(0).get(3);
		charMatrixTwo[2] = data.get(1).get(2);
		charMatrixTwo[3] = data.get(1).get(3);
		
		charMatrixThree[0] = data.get(2).get(0);
		charMatrixThree[1] = data.get(2).get(1);
		charMatrixThree[2] = data.get(3).get(0);
		charMatrixThree[3] = data.get(3).get(1);
		
		charMatrixFour[0] = data.get(2).get(2);
		charMatrixFour[1] = data.get(2).get(3);
		charMatrixFour[2] = data.get(3).get(2);
		charMatrixFour[3] = data.get(3).get(3);
		
		Matrix[] mArr = new Matrix[4];
		mArr[0] = new Matrix(new String(charMatrixOne), 2);
		mArr[1] = new Matrix(new String(charMatrixTwo), 2);
		mArr[2] = new Matrix(new String(charMatrixThree), 2);
		mArr[3] = new Matrix(new String(charMatrixFour), 2);
		
		return mArr;
	}
	
	public void print() {
		for(int i = 0; i < data.size(); i++) {
			for(int k = 0; k < data.get(i).size(); k++) {
				System.out.print(data.get(i).get(k));
			}
			System.out.println();
		}
		
		System.out.println();
	}
	
	public int active() {
		int count = 0;
		
		for(int i = 0; i < data.size(); i++) {
			for(int j = 0; j < data.get(i).size(); j++) {
				if(data.get(i).get(j) == '#') {
					count++;
				}
			}
		}
		
		return count;
	}
}
