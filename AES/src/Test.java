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
	static String result_enc = "./files/results/result_enc";
	static String result_dec = "./files/results/result_dec";

	public static void test_aes1(String key_path, String msg_path, String Cipher_path) {
		System.out.println("   Test - AES1:");
		BlocksList msg = new BlocksList(msg_path);
		BlocksList key = new BlocksList(key_path);
		Block key1 = key.getBlock(0);

		//enc:
		BlocksList enc = AES1.enc(msg, key1);
		//		enc.print();
		enc.writeToBytesFile(result_enc);
		//dec:
		BlocksList cipher = new BlocksList(result_enc);
		BlocksList dec = AES1.dec(cipher , key1);
		//		dec.print();
		dec.writeToBytesFile(result_dec);

		System.out.print("original msg: ");
		read(msg_path);
		//		msg.print();
		//		printFile(message_short);

		System.out.print("after dec:    ");
		read(result_dec);
		//		dec.print();
		//		printFile(res1_msg);
		System.out.println();
	}

	public static void test_aes2(String key_path, String msg_path, String Cipher_path) {
		System.out.println("   Test - AES2:");
		BlocksList msg = new BlocksList(msg_path);
		BlocksList key = new BlocksList(key_path);
		Block key1 = key.getBlock(0);
		Block key2 = key.getBlock(0);

		BlocksList original_cipher = new BlocksList(msg_path);

		//enc:
		BlocksList enc = AES2.enc(msg, key1, key2);
		//				enc.print();
		enc.writeToBytesFile(result_enc);
		//dec:
		BlocksList cipher = new BlocksList(result_enc);
		BlocksList dec = AES2.dec(cipher, key1, key2);
		//	dec.print();
		dec.writeToBytesFile(result_dec);

		System.out.print("original msg: ");
		read(msg_path);
		// msg.print();
		// printFile(message_short);

		System.out.print("after dec:    ");
		read(result_dec);
		// dec.print();
		//	printFile(res1_msg);

		System.out.println(cipher.equals(original_cipher));

	}


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