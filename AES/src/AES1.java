
public class AES1 {
	
	public static BlocksList enc(BlocksList msg, Block key) {
		msg.swapeIndexes();
		return addRoundKey(msg, key);
	}
	
	public static BlocksList dec(BlocksList msg, Block key) {
		BlocksList block = addRoundKey(msg, key);
		block.swapeIndexes();
		return block;
	}

	
	
	public static void swapeIndexes(Block block) {
		block.swipeIndexex();
	}
	
	public static BlocksList addRoundKey(BlocksList msg, Block key) {
		BlocksList res = new BlocksList();
		for(int i=0; i<msg.getSize(); i++) {
			Block b = addRoundKey(msg.getBlock(i), key);
			res.addBlock(b);
		}
		return res;
	}

	private static Block addRoundKey(Block msg, Block key) {
		int rows = msg.getRows(), cols = msg.getCols();
		Block xorMatrix = new Block();
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				String b1 = msg.getValue(row, col);
				String b2 = key.getValue(row, col);
				xorMatrix.add(Bases.xorHexes(b1,b2));
			}
		}
		return xorMatrix;
	}
	
	
	
	

}
