import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	static String message_short = "./files/message_short";
	static String message_long = "./files/message_long";
	static String cipher_short = "./files/cipher_short";
	static String cipher_long = "./files/cipher_long";
	static String key_short = "./files/key_short";
	static String key_long = "./files/key_long";

	static String res1_cipher = "./files/ronald/res1_cipher";
	static String res1_msg = "./files/ronald/res1_msg";

	static BlocksList msg = new BlocksList(message_long);
	static BlocksList key = new BlocksList(key_short);
	static Block key1 = key.getBlock(0);

	public static void main(String[] args) {
		test_aes1();
		
//		byte b = Bases.hexToByte("9");
//		System.out.println(b);
		
		
//		msg.print();
//		msg.swapeIndexes();
//		BlocksList res = msg.xor(key1);
//		res.writeToBytesFile("./files/ron/enc");
//		BlocksList enc = new BlocksList("./files/ron/enc");
//		System.out.println("after enc:");
//		enc.print();
//		
//	
//		BlocksList a = res.xor(key1);
//		a.swapeIndexes();
//		
//		System.out.println("after dec:");
//		a.print();
//		a.printBytes();
//		res.writeToBytesFile("./files/ron/dec");
		
		
	}

	public static void read(String path) {
		@SuppressWarnings("resource")
		Reader bytestream;
		try {
			bytestream = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), "ISO-8859-1"));
			int unsignedByte;
			while((unsignedByte = bytestream.read()) != -1)
				System.out.print((char)unsignedByte);
			System.out.println();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void printFile(String path) {
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(Paths.get(path));
			//			Bases.printCharsByBytesFile(bytes);
			Bases.printBytesDetails(bytes);
		}catch (Exception e) {}
	}

	public static void test_aes1() {
		//enc:
		BlocksList enc = AES1.enc(msg, key1);
//		enc.print();
		enc.writeToBytesFile(res1_cipher);
		//dec:
		BlocksList aes1_cipher = new BlocksList(res1_cipher);
		BlocksList dec = AES1.dec(aes1_cipher, key1);
//		dec.print();
		dec.writeToBytesFile(res1_msg);

		System.out.print("original msg: ");
		read(message_long);
//		msg.print();
//		printFile(message_short);
		
		System.out.print("after dec:    ");
		read(res1_msg);
//		dec.print();
//		printFile(res1_msg);



	}


	//	********* Additional Functions ********* //
	public static void print(Object o) {
		System.out.println(o.toString());
	}

}
