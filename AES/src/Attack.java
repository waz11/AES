public class Attack {
	private Block m;
	private Block c;
	private Block a;
	private BlockList k;
	private Block k1;
	private Block k2;
	
	
	public Attack (String msg_path, String cipher_path) {
		BlockList full_msg = new BlockList(msg_path);
		BlockList full_cipher = new BlockList(cipher_path);
		this.m = full_msg.getBlock(0);
		this.c = full_cipher.getBlock(0);
		k = new BlockList();
		this.a = c.xor(m);
	}
	
	
	public BlockList breakAES2(String output_path) {
		// build a fake key1:
		this.k1 = new Block();
		byte b = 1;
		String hex = Bases.byteToHex(b);
		while(!k1.isFull())
			k1.add(hex);

		// conclude the fake k2: 
		this.k2 = a.xor(k1);

		this.k.addBlock(this.k1);
		this.k.addBlock(this.k2);

		writeKeysToFile(output_path);
		return this.k;
	}
	
	
	private void writeKeysToFile(String output_path) {
		BlockList keys = new BlockList();
		keys.addBlock(this.k1);
		keys.addBlock(this.k2);
		keys.writeToBytesFile(output_path);
	}

	public boolean test(String cipher_path, String expected_msg_path) {
		BlockList cipher = new BlockList(cipher_path);
		BlockList expected_msg = new BlockList(expected_msg_path);
		BlockList breaking = AES2.dec(cipher, this.k1, this.k2);
		return breaking.isEquals(expected_msg);
	}
}


