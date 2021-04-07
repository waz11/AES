import java.util.HashMap;
import java.util.Map;

public class Attack {
	public static void breakAES2(String msg_path, String cipher_path, String output_path) {
		Map<String, byte[]> map = new HashMap<>();
		for(Integer i=0; i<256; i++) {
			byte b = i.byteValue();
			Bases.printByte(b);
		}
		
		
	}
}
