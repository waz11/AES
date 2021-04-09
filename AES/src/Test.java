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
	String key_path;
	String expected_msg_path;
	String expected_cipher_path;
	
	String result_enc_path = "./files/results/result_enc";
	String result_dec_path = "./files/results/result_dec";
	
	BlockList expected_cipher;
	BlockList expected_msg;
	
	public Test(String key_path, String expected_msg_path, String expected_cipher_path) {
		this.key_path = key_path;
		this.expected_msg_path = expected_msg_path;
		this.expected_cipher_path = expected_cipher_path;
		this.expected_cipher = new BlockList(this.expected_cipher_path);
		this.expected_msg = new BlockList(this.expected_msg_path);
	}

	public boolean run() {
		boolean ans1 = test_Enc();
		boolean ans2 = test_Dec();
		return ans1 && ans2;
	}

	public boolean test_Enc() {
		BlockList this_cipher = AES2.enc(expected_msg_path, key_path, result_enc_path);
//		printEnc(this_cipher);
		return this_cipher.isEquals(this.expected_cipher);
	}

	public boolean test_Dec() {
		BlockList this_msg1 = AES2.dec(expected_cipher_path, key_path, result_dec_path);
//		printDec(this_msg1);
		boolean ans1 = this_msg1.isEquals(this.expected_msg);

		BlockList this_msg2 = AES2.dec(result_enc_path, key_path, result_dec_path);
//		printDec(this_msg2);
		boolean ans2 = this_msg2.isEquals(this.expected_msg);
		
		return ans1 && ans2;
	}

	// prints:
	public void printEnc(BlockList result) {
		System.out.print("original cipher:  ");
		expected_cipher.printBytes();

		System.out.print("this cipher:      ");
		result.printBytes();
	}

	public void printDec(BlockList result) {
		System.out.print("original msg:  ");
		expected_msg.printBytes();

		System.out.print("this msg:      ");
		result.printBytes();
	}


	//	Additional Functions:
	public void read(String path) {
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
	public void printFile(String path) {
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(Paths.get(path));
			//			Bases.printCharsByBytesFile(bytes);
			Bases.printBytesDetails(bytes);
		}catch (Exception e) {}
	}
}