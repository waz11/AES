import java.io.*;
import java.nio.file.Files;


public class Main {
	public static void main(String[] args) {

		String[][] block = toHexBlock("./files/message_short");
		print2DArr(block);
		System.out.println();
		swipeIndexex(block);
		print2DArr(block);
	}

	public static void swipeIndexex(String[][] block){
		int rows = block.length, cols = block[0].length;
		for(int row = 0; row < rows; row++) {
			for(int col = row + 1; col < cols; col++) {
				swipe(block, row, col);
			}
		}
	}

	public static void swipe(String[][] block, int i, int j) {
		String temp = block[i][j];
		block[i][j] = block[j][i];
		block[j][i] = temp;
	}



	public static String[][] toHexBlock(String path) {
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
					final String hexString = Integer.toHexString(b & 0xff);
					if(hexString.length()==1)
						s[i][j] = "0";
					s[i][j] = hexString;
					i++;
				}
		} catch (IOException e) {}
		return s;
	}

	public static void print(Object o) {
		System.out.println(o.toString());
	}

	public static void printArr(char[] arr) {
		for(Object o:arr)
			System.out.print(o+" ");
	}

	public static void print2DArr(String[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j]);
				if(j<4) System.out.print(" ");
			}
			System.out.println();
		}
	}


}
