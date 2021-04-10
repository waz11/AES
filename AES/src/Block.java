import java.io.FileOutputStream;

public class Block {
	
	private String[][] values;
	private int rows;
	private int cols;
	private int size;
	private int lastRow;
	private int lastCol;

	public Block(int rows, int cols) {
		this.rows = rows; this.cols = cols;
		this.size = rows*cols;
		this.values = new String[rows][cols];
		this.lastRow = 0; this.lastCol = 0;
	}
	
	public Block(Block other) {
		this.values = new String[rows][cols];
		this.lastRow = 0; this.lastCol = 0;
		for(int row=0; row<this.rows; row++) {
			for(int col=0; col<this.cols; col++) {
				this.values[row][col] = other.getValue(row, col);
			}
		}
		
	}

	//	operations:
	public void add(String input) {
		if(!this.isFull()) {
			this.values[this.lastRow][this.lastCol] = input;
			this.increaseIndex();
		}
	}
	public void increaseIndex() {
		if(lastRow<this.rows) {
			if(lastCol == this.rows - 1) {
				lastCol = 0;
				lastRow++;
			}
			else if(lastCol < this.cols-1) {
				lastCol++;
			}
		}
	}
	public void changeValue(int row, int col, String value) {
		values[row][col] = value;
	}
	public void swapeIndexex(){
		for(int row = 0; row < this.rows; row++) {
			for(int col = row + 1; col < this.cols; col++) {
				swipe(row, col);
			}
		}
	}
	public Block xor(Block other) {
		Block result = new Block(other.getRows(), other.getCols());
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
	
	public void writeToBytesFile(String path) {
		byte[] data = this.blockToBytes();
		try (FileOutputStream fos = new FileOutputStream(path, true)) {
			fos.write(data);
			fos.close();
		} catch (Exception e) {}
	}
	
	
	
	public byte[] blockToBytes() {
		byte[] res = new byte[this.size];
		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				String hex = this.getValue(row, col);
				byte b = Bases.hexToByte(hex);
				res[row * this.rows + col] = b;
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
