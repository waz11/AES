
public class AES2 {
	
	public static BlocksList enc(BlocksList msg, Block key1, Block key2) {
		return AES1.enc(AES1.enc(msg, key1),key2);
	}
	
	public static BlocksList dec(BlocksList msg, Block key1, Block key2) {
		return AES1.dec(AES1.dec(msg, key2),key1);
	}
	
}
