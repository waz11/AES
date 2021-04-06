
public class Block {
	private String[][] values;
	private int rows = 4;
	private int cols = 4;
	private int size = rows*cols;
	private int lastRow;
	private int lastCol;

	public Block() {
		this.values = new String[4][4];
		this.lastRow = 0; this.lastCol = 0;
	}

	//	operations:
	public void add(String input) {
		if(!this.isFull()) {
			this.values[this.lastRow][this.lastCol] = input;
			this.increaseIndex();
		}
	}
	public void increaseIndex() {
		if(lastRow<=3) {
			if(lastCol == rows-1) {
				lastCol = 0;
				lastRow++;
			}
			else if(lastCol < cols-1) {
				lastCol++;
			}
		}
	}
	public void changeValue(int row, int col, String value) {
		values[row][col] = value;
	}
	public void swipeIndexex(){
		for(int row = 0; row < this.rows; row++) {
			for(int col = row + 1; col < this.cols; col++) {
				swipe(row, col);
			}
		}
	}
	public Block xor(Block other) {
		Block result = new Block();
		for(int row = 0; row < this.rows; row++) {
			for(int col = 0; col < this.cols; col++) {
				String b1 = this.getValue(row, col);
				String b2 = other.getValue(row, col);
				result.add(Bases.xorHexes(b1,b2));
			}
		}
		return result;
	}

	
	//	status:
	public boolean isFull() {
		return (lastRow >= rows) || (lastCol >= cols);
	}
	public boolean isEquals(Block other) {
		for(int row=0; row<rows; row++) {
			for(int col=0; col<cols; col++) {
				String val1 = this.getValue(row, col);
				String val2 = other.getValue(row, col);
				if(!(val1.equals(val2))) return false;
			}
		}
		return true;
	}
	
	
	// converts:
	public byte[] blockToBytes() {
		byte[] res = new byte[this.size];
		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				String hex = this.getValue(row, col);
				byte b = Bases.hexToByte(hex);
				res[row * 4 + col] = b;
			}
		}
		return res;
	}


	// getters & setters:
	public String getValue(int row, int col) {
		return values[row][col];
	}
	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}


	// prints:
	public void print() {
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				System.out.print(this.values[i][j]);
				if(j < this.cols) System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public void printBytesDetails() {
		byte[] b = this.blockToBytes();
		Bases.printBytesDetails(b);
	}
	

	//	********* Additional Functions ********* //
	private void swipe(int i, int j) {
		String temp = this.values[i][j];
		this.values[i][j] = this.values[j][i];
		this.values[j][i] = temp;
	}

}
