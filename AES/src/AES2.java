
public class AES2 {
	
	public static BlockList enc(String path_msg, String Path_key, String path_result) {
		BlockList msg = new BlockList(path_msg);
		BlockList key = new BlockList(Path_key);
		Block key1 = key.getBlock(0);
		Block key2 = key.getBlock(1);
		
		BlockList result = AES1.enc(AES1.enc(msg, key1),key2);
		result.writeToBytesFile(path_result);
		System.out.println("...Encryption Done!");
		return result;
	}
	public static BlockList dec(String path_cipher, String Path_key, String path_result) {
		BlockList cipher = new BlockList(path_cipher);
		BlockList key = new BlockList(Path_key);
		Block key1 = key.getBlock(0);
		Block key2 = key.getBlock(1);

		BlockList result = AES1.dec(AES1.dec(cipher, key2),key1);
		result.writeToBytesFile(path_result);
		System.out.println("...Decryption Done!");
		return result;
	}

	public static BlockList enc(BlockList msg, Block key1, Block key2) {
		System.out.println("...Encryption Done!");
		return AES1.enc(AES1.enc(msg, key1),key2);
	}
	public static BlockList dec(BlockList cipher, Block key1, Block key2) {
		System.out.println("...Decryption Done!");
		return AES1.dec(AES1.dec(cipher, key2),key1);
	}
	
}
