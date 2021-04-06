import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {

	public static void test_Enc(String key_path, String msg_path, String expected_cipher_path, String result_enc_path) {
		System.out.println("Test- AES2 - Enc:");
		BlocksList expected_cipher = new BlocksList(expected_cipher_path);
		BlocksList this_cipher = AES2.enc(msg_path, key_path, result_enc_path);
		System.out.println("this cipher:");
		this_cipher.print();
		System.out.println("original cipher:");
		expected_cipher.print();

		System.out.println(this_cipher.equals(expected_cipher)+"\n");
	}

	public static void test_Dec(String key_path, String cipher_path, String expected_msg_path, String result_dec_path) {
		System.out.println("Test- AES2 - Dec:");
		BlocksList expected_msg = new BlocksList(expected_msg_path);
		BlocksList this_msg = AES2.dec(cipher_path, key_path, result_dec_path);
		System.out.println("this msg:");
		this_msg.print();
		System.out.println("original msg:");
		expected_msg.print();

		System.out.println(this_msg.equals(expected_msg));
	}




	//	Additional Functions:
	public static void read(String path) {
		Reader bytestream;
		try {
			bytestream = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), "ISO-8859-1"));
			int unsignedByte;
			while((unsignedByte = bytestream.read()) != -1)
				System.out.print((char)unsignedByte);
			System.out.println();
		}
		catch (UnsupportedEncodingException | FileNotFoundException e) {}
		catch (IOException e) {}

	}
	public static void printFile(String path) {
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(Paths.get(path));
			//			Bases.printCharsByBytesFile(bytes);
			Bases.printBytesDetails(bytes);
		}catch (Exception e) {}
	}
}