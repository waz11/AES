
public class AES2 {
	
	public static BlocksList enc(String path_msg, String Path_key, String path_result) {
		BlocksList msg = new BlocksList(path_msg);
		BlocksList key = new BlocksList(Path_key);
		Block key1 = key.getBlock(0);
		Block key2 = key.getBlock(1);
		
		BlocksList result = AES1.enc(AES1.enc(msg, key1),key2);
		result.writeToBytesFile(path_result);
		System.out.println("Encryption Done!");
		return result;
	}
	public static BlocksList dec(String path_cipher, String Path_key, String path_result) {
		BlocksList cipher = new BlocksList(path_cipher);
		BlocksList key = new BlocksList(Path_key);
		Block key1 = key.getBlock(0);
		Block key2 = key.getBlock(1);

		BlocksList result = AES1.dec(AES1.dec(cipher, key2),key1);
		result.writeToBytesFile(path_result);
		System.out.println("Decryption Done!");
		return result;
	}

	public static BlocksList enc(BlocksList msg, Block key1, Block key2) {
		System.out.println("Encryption Done!");
		return AES1.enc(AES1.enc(msg, key1),key2);
	}
	public static BlocksList dec(BlocksList msg, Block key1, Block key2) {
		System.out.println("Decryption Done!");
		return AES1.dec(AES1.dec(msg, key2),key1);
	}
	
}
