import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Main {
	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

	public static void main(String[] args) {

		print(toHexString("./files/message_short"));


	}

	public static List<String> toHexString(String path) {
		List<String> sb = new LinkedList<>();
		File file = new File(path);
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(file.toPath());
			if (bytes != null) 
				for (byte b:bytes) {
					final String hexString = Integer.toHexString(b & 0xff);
					if(hexString.length()==1)
						sb.add("0");
					sb.add(hexString);
				}
		} catch (IOException e) {}
		return sb;
	}

	public static void print(Object o) {
		System.out.println(o.toString());
	}

	public static void printArr(char[] arr) {
		for(Object o:arr)
			System.out.print(o+" ");
	}


}
