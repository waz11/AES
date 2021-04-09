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
	private BlockList keys;
	private BlockList expected_msg;
	private BlockList expected_cipher;
	
	private BlockList enc;
	private BlockList dec;
	
	private Block key1;
	private Block key2;

	public Test(String key_path, String expected_msg_path, String expected_cipher_path) {
		this.keys = new BlockList(key_path);
		this.expected_msg = new BlockList(expected_msg_path);
		this.expected_cipher = new BlockList(expected_cipher_path);
		this.key1 = keys.getBlock(0);
		this.key2 = keys.getBlock(1);
	}

	public boolean run() {
		BlockList msg = new BlockList(this.expected_msg);
		enc = AES2.enc(msg, this.key1, this.key2);
		BlockList cipher = new BlockList(this.expected_cipher);
		dec = AES2.dec(cipher, this.key1, this.key2);
		boolean t1 = enc.isEquals(this.expected_cipher);
		boolean t2 = dec.isEquals(this.expected_msg);
		return t1 && t2;
	}


	// prints:
	public void printEnc(BlockList result) {
		System.out.print("original cipher:  ");
		expected_cipher.printChars();

		System.out.print("this cipher:      ");
		result.printChars();
	}

	public void printDec(BlockList result) {
		System.out.print("original msg:  ");
		expected_msg.printChars();

		System.out.print("this msg:      ");
		result.printChars();
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