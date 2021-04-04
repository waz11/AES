
public class AES2 {
	public static BlocksList encryption(BlocksList msg, BlocksList key1, BlocksList key2) {
		return AES1.enc(AES1.enc(msg, key1),key2);
	}
	
	public static BlocksList decryption(BlocksList msg, BlocksList key1, BlocksList key2) {
		return AES1.dec(AES1.dec(msg, key2),key1);
	}
}
