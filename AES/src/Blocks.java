import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

public class Blocks {

	public static String[][] fileToHexBlock(String path) {
		String[][] s = new String[4][4];
		File file = new File(path);
		int i=0,j=0;
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(file.toPath());
			if (bytes != null) 
				for (byte b:bytes) {
					if (i==4) {
						i=0;
						j++;
					}
					if(i < 4 && j < 4) {
						String hexString = Basics.byteToHex(b);
						s[i][j] = hexString;
						i++;
					}
				}
		} catch (IOException e) {}
		return s;
	}


	public static void swipeIndexex(String[][] block){
		int rows = block.length, cols = block[0].length;
		for(int row = 0; row < rows; row++) {
			for(int col = row + 1; col < cols; col++) {
				swipe(block, row, col);
			}
		}
	}

	
	public static String[][] addRoundKey(String[][] block1, String[][] block2) {
		int rows = block1.length, cols = block1[0].length;
		String[][] xorMatrix = new String[rows][cols];
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				String b1 = block1[row][col];
				String b2 = block2[row][col];
				xorMatrix[row][col] = Basics.xorHexes(b1,b2);
			}
		}
		return xorMatrix;
	}



	//	********* Additional Functions ********* //
	private static void swipe(String[][] block, int i, int j) {
		String temp = block[i][j];
		block[i][j] = block[j][i];
		block[j][i] = temp;
	}


}
