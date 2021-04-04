
public class AES {
	
	public static void swipeIndexes(Block block) {
		block.swipeIndexex();
	}
	
	public static BlocksList addRoundKey(BlocksList msg, BlocksList key) {
		BlocksList res = new BlocksList();
		for(int i=0; i<msg.getSize(); i++) {
			Block b = addRoundKey(msg.getBlock(i), key.getBlock(i%key.getSize()));
			res.addBlock(b);
		}
		return res;
	}

	public static Block addRoundKey(Block block1, Block block2) {
		int rows = block1.getRows(), cols = block1.getCols();
		Block xorMatrix = new Block();
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				String b1 = block1.getValue(row, col);
				String b2 = block2.getValue(row, col);
				xorMatrix.add(Basics.xorHexes(b1,b2));
			}
		}
		return xorMatrix;
	}

}
