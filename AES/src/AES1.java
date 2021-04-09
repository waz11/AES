public class AES1 {
	
	public static BlockList enc(BlockList msg, Block key) {
		msg.swapeIndexes();
		return addRoundKey(msg, key);
	}
	
	public static BlockList dec(BlockList msg, Block key) {
		BlockList blocks = addRoundKey(msg, key);
		blocks.swapeIndexes();
		return blocks;
	}

	private static BlockList addRoundKey(BlockList msg, Block key) {
		return msg.xor(key);
	}

}
