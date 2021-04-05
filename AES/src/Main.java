
public class Main {
	
	public static void main(String[] args) {
		BlocksList msg = new BlocksList("./files/message_short");
		BlocksList key = new BlocksList("./files/key_short");
		BlocksList cipher = new BlocksList("./files/cipher_short");
		
		Block key1 = key.getBlock(0);
		Block key2 = key.getBlock(1);
//		
//		BlocksList enc = AES2.encryption(msg, key1, key2);
//		enc.writeToByteFile("./files/ronald/enc1");
		
		BlocksList cipher1 = new BlocksList("./files/ronald/enc1");
//		BlocksList key = new BlocksList("./files/key_short");
		
		
		BlocksList dec = AES2.decryption(cipher, key1, key2);
		dec.writeToByteFile("./files/ronald/dec1");
		
		boolean ans = cipher1.isEquals(cipher);
		System.out.println(ans);
		
	}


	//	********* Additional Functions ********* //
	public static void print(Object o) {
		System.out.println(o.toString());
	}

	
	public static void test() {
		// read M
		BlocksList msg = new BlocksList("./files/message_long");
		print("M:");
		msg.print();
		
		//save block to file of Bytes
		msg.writeToByteFile("./files/results/tring1");

		// swipe indexes
		msg.swapeIndexes();
		print("M - Swipe Indexes:");
		msg.print();

		// read key:
		BlocksList key = new BlocksList("./files/key_short");
		print("Key:");
		key.print();

		BlocksList roundKey = AES1.addRoundKey(msg, key.getBlock(0));
		print("Round Key:");
		roundKey.print();
	}
	
	
}
