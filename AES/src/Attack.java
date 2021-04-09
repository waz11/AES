public class Attack {

	public static BlockList breakAES2(String msg_path, String cipher_path, String output_path) {
		BlockList full_mssg = new BlockList(msg_path);
		BlockList full_cipher = new BlockList(cipher_path);

		// get only the first block(first 16 Byte):
		Block m =  full_mssg.getBlock(0);
		Block c =  full_cipher.getBlock(0);

		// get the 'a block':
		Block a = c.xor(m);

		// build a fake key1:
		Block k1 = new Block();
		byte b = 1;
		String hex = Bases.byteToHex(b);
		while(!k1.isFull())
			k1.add(hex);

		// conclude the fake k2: 
		Block k2 = a.xor(k1);

		BlockList keys = new BlockList();
		keys.addBlock(k1);
		keys.addBlock(k2);

		keys.writeToBytesFile(output_path);
		return keys;
	}

	public static boolean test() {
		String message_short = "./files/message_short";
		String message_long = "./files/message_long";
		String cipher_short = "./files/cipher_short";
		String cipher_long = "./files/cipher_long";

		String breaking = "./files/output/break/fake_keys";
		String break_msg_short = "./files/output/break/break_msg_short";
		String break_msg_long = "./files/output/break/break_msg_long";

		AES2.dec(cipher_short, breaking, break_msg_short);
		AES2.dec(cipher_long, breaking, break_msg_long);

		BlockList original_msg_short = new BlockList(message_short);	
		BlockList original_msg_long = new BlockList(message_long);

		BlockList breakresult_msg_short = new BlockList(message_short);	
		BlockList breakresult_msg_long = new BlockList(message_long);

		boolean a = original_msg_short.isEquals(breakresult_msg_short);
		boolean b = original_msg_long.isEquals(breakresult_msg_long);
		
		return a && b;
	}
}


