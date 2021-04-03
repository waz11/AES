import java.io.*;
import java.nio.file.Files;


public class Main {
//	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

	public static void main(String[] args) {

		String[][] s = toHexString("./files/message_short");
		print2DArr(s);
		
		
	}

	public static String[][] toHexString(String path) {
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
