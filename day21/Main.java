import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class Main {

	public static HashMap<Matrix, Matrix> hashMap = new HashMap<Matrix, Matrix>();
	public static Vector<Vector<Matrix>> picture = new Vector<Vector<Matrix>>();

	public static void main(String[] args) {

		readIn("input.txt");

		Vector<Matrix> row = new Vector<Matrix>();

		// initialization
		row.add(new Matrix(".#...####", 3));
		picture.add(row);

		// do 5 iterations
		for (int l = 0; l < 18; l++) {
			picture = splitPicture(picture);
			
			picture = updatePicture(picture);
			
			int count = 0;
			for (int i = 0; i < picture.size(); i++) {
				for (int k = 0; k < picture.get(i).size(); k++) {
					count += picture.get(i).get(k).active();
				}
			}
			
			System.out.println((l+1) + ": " + count);
		}
	}

	@SuppressWarnings("rawtypes")
	public static Vector<Vector<Matrix>> updatePicture(Vector<Vector<Matrix>> picture) {
		for (int i = 0; i < picture.size(); i++) {
			for (int k = 0; k < picture.get(i).size(); k++) {
				Matrix[] variants = new Matrix[12];

				variants[0] = picture.get(i).get(k);
				variants[1] = rotateLeft(variants[0], 1);
				variants[2] = flipHorizontal(variants[0]);
				variants[3] = flipVertical(variants[0]);
				variants[4] = flipHorizontal(variants[1]);
				variants[5] = flipVertical(variants[1]);
				variants[6] = rotateLeft(variants[0], 2);
				variants[7] = flipHorizontal(variants[6]);
				variants[8] = flipVertical(variants[6]);
				variants[9] = rotateLeft(variants[0], 3);
				variants[10] = flipHorizontal(variants[9]);
				variants[11] = flipVertical(variants[9]);

				boolean match = false;
				for (int m = 0; m < variants.length; m++) {
					if(match) {
						break;
					}
					Iterator it = hashMap.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry p = (Map.Entry) it.next();
						Matrix key = (Matrix) p.getKey();
						if (key.equals(variants[m])) {							
							picture.get(i).add(k, hashMap.get(key));
							picture.get(i).remove(k + 1);
							match = true;
							break;
						}
					}
				}
			}
		}

		return picture;
	}

	public static Vector<Vector<Matrix>> splitPicture(Vector<Vector<Matrix>> picture) {
		Vector<Vector<Matrix>> updatedPicture = new Vector<Vector<Matrix>>();
		
		if (picture.get(0).get(0).dim > 3) {
			for (int i = 0; i < picture.size(); i++) {
				Vector<Matrix> row = new Vector<Matrix>();
				Vector<Matrix> row2 = new Vector<Matrix>();
				for (int k = 0; k < picture.get(i).size(); k++) {
					Matrix[] split = picture.get(i).get(k).split();
					row.add(split[0]);
					row.add(split[1]);
					row2.add(split[2]);
					row2.add(split[3]);
				}
				updatedPicture.add(row);
				updatedPicture.add(row2);
			}
			return updatedPicture;
		}

		return picture;
	}

	public static Matrix rotateLeft(Matrix m, int rotateAmount) {
		if (rotateAmount <= 0) {
			return null;
		}

		Matrix tmp = m;

		for (int i = 0; i < rotateAmount; i++) {
			tmp = rotateLeft(tmp);
		}

		return tmp;
	}

	public static Matrix rotateLeft(Matrix m) {
		char[][] data = new char[m.dim][m.dim];
		char[][] data2 = new char[m.dim][m.dim];

		for (int i = 0; i < m.dim; i++) {
			for (int k = 0; k < m.dim; k++) {
				data[i][k] = m.data.get(i).get(k);
			}
		}

		for (int i = 0; i < m.dim; i++) {
			for (int k = 0; k < m.dim; k++) {
				data2[i][k] = data[k][i];
			}
		}

		// convert 2d char array to 1d string
		char[] charStr = new char[m.dim * m.dim];
		for (int i = 0; i < m.dim; i++) {
			for (int k = 0; k < m.dim; k++) {
				charStr[i * m.dim + k] = data2[i][k];
			}
		}

		return flipHorizontal(new Matrix(new String(charStr), m.dim));
	}

	public static Matrix flipHorizontal(Matrix m) {
		char[][] data = new char[m.dim][m.dim];

		// convert vector to char array
		for (int i = 0; i < m.dim; i++) {
			for (int k = 0; k < m.dim; k++) {
				data[i][k] = m.data.get(i).get(k);
			}
		}

		// switch data[0] and data[m.dim-1]
		// flipHorizontal transformation
		char tmp = 0;
		for (int i = 0; i < m.dim; i++) {
			tmp = data[0][i];
			data[0][i] = data[m.dim - 1][i];
			data[m.dim - 1][i] = tmp;
		}

		// convert 2d char array to 1d string
		char[] charStr = new char[m.dim * m.dim];
		for (int i = 0; i < m.dim; i++) {
			for (int k = 0; k < m.dim; k++) {
				charStr[i * m.dim + k] = data[i][k];
			}
		}

		return new Matrix(new String(charStr), m.dim);
	}

	public static Matrix flipVertical(Matrix m) {
		char[][] data = new char[m.dim][m.dim];

		// convert vector to char array
		for (int i = 0; i < m.dim; i++) {
			for (int k = 0; k < m.dim; k++) {
				data[i][k] = m.data.get(i).get(k);
			}
		}

		// switch data[0] and data[m.dim-1]
		// flipHorizontal transformation
		char tmp = 0;
		for (int i = 0; i < m.dim; i++) {
			tmp = data[i][0];
			data[i][0] = data[i][m.dim - 1];
			data[i][m.dim - 1] = tmp;
		}

		// convert 2d char array to 1d string
		char[] charStr = new char[m.dim * m.dim];
		for (int i = 0; i < m.dim; i++) {
			for (int k = 0; k < m.dim; k++) {
				charStr[i * m.dim + k] = data[i][k];
			}
		}

		return new Matrix(new String(charStr), m.dim);
	}

	public static void readIn(String path) {
		BufferedReader reader = null;
		String line = "";
		String split = " => ";

		try {
			reader = new BufferedReader(new FileReader(path));
			while ((line = reader.readLine()) != null) {
				String[] matrixString = line.split(split);

				Matrix key = null;
				Matrix value = null;
				if (matrixString[0].length() == 5) {
					key = new Matrix(matrixString[0].replaceAll("/", ""), 2);
					value = new Matrix(matrixString[1].replaceAll("/", ""), 3);
				} else {
					key = new Matrix(matrixString[0].replaceAll("/", ""), 3);
					value = new Matrix(matrixString[1].replaceAll("/", ""), 4);
				}

				hashMap.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}