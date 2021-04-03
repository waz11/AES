
public class Block {
	private String[][] values;
	private int rows;
	private int cols;
	
	public Block(String path) {
		
	}
	
	public void changeValue(int row, int col, String value) {
		values[row][col] = value;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
}
