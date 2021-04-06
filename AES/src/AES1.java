
public class AES1 {
	
	public static BlocksList enc(BlocksList msg, Block key) {
		msg.swapeIndexes();
		return addRoundKey(msg, key);
	}
	
	public static BlocksList dec(BlocksList msg, Block key) {
		BlocksList blocks = addRoundKey(msg, key);
		blocks.swapeIndexes();
		return blocks;
	}

	public static BlocksList addRoundKey(BlocksList msg, Block key) {
		return msg.xor(key);
	}

	
	
	
	

}
