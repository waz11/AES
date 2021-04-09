
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


}

