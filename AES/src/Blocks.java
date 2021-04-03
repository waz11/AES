import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Blocks {
	public static void swipeIndexex(String[][] block){
		int rows = block.length, cols = block[0].length;
		for(int row = 0; row < rows; row++) {
			for(int col = row + 1; col < cols; col++) {
				swipe(block, row, col);
			}
		}
	}

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
					String hexString = Basics.byteToHex(b);
					s[i][j] = hexString;
					i++;
				}
		} catch (IOException e) {}
		return s;
	}

	//	********* Additional Functions ********* //
	private static void swipe(String[][] block, int i, int j) {
		String temp = block[i][j];
		block[i][j] = block[j][i];
		block[j][i] = temp;
	}


}
