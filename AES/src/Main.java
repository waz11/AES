import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	static String message_short = "./files/message_short";
	static String cipher_short = "./files/cipher_short";
	static String key_short = "./files/key_short";

	static String res1_cipher = "./files/ronald/res1_cipher";
	static String res1_msg = "./files/ronald/res1_msg";

	static BlocksList msg = new BlocksList(message_short);
	static BlocksList key = new BlocksList(key_short);
	static Block key1 = key.getBlock(0);

	public static void main(String[] args) {
		test_aes1();


	}

	public static void read(String path) throws IOException {
		Reader bytestream = new BufferedReader(new InputStreamReader(
				new FileInputStream(path), "ISO-8859-1"));
		int unsignedByte;
		while((unsignedByte = bytestream.read()) != -1)
			System.out.print((char)unsignedByte);
		System.out.println();
	}

	public static void printFile(String path) {
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(Paths.get(path));
			Bases.printCharsByBytesFile(bytes);
			Bases.printBytesDetails(bytes);
		}catch (Exception e) {}
	}

	public static void test_aes1() {
		//enc:
		BlocksList enc = AES1.enc(msg, key1);
		enc.writeToBytesFile(res1_cipher);
		//dec:
		BlocksList aes1_cipher = new BlocksList(res1_cipher);
		BlocksList dec = AES1.dec(aes1_cipher, key1);
		dec.writeToBytesFile(res1_msg);

		try {
			System.out.print("original msg: ");
			read(message_short);
			printFile(message_short);
			System.out.print("after dec: ");
			read(res1_msg);
			printFile(res1_msg);
		} catch (IOException e) {}
	}


	//	********* Additional Functions ********* //
	public static void print(Object o) {
		System.out.println(o.toString());
	}

}
