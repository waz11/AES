
public class Main {
	static String message_short = "./files/message_short";
	static String message_long = "./files/message_long";
	static String cipher_short = "./files/cipher_short";
	static String cipher_long = "./files/cipher_long";
	static String key_short = "./files/key_short";
	static String key_long = "./files/key_long";
	
	static String enc_result_path = "./files/results/result_enc";
	static String dec_result_path = "./files/results/result_dec";

	public static void main(String[] args) {
		Test.test_Enc(key_short, message_short, cipher_short, enc_result_path);
		Test.test_Dec(key_short, enc_result_path, cipher_short, dec_result_path);
	}

	public static void print(Object o) {
		System.out.println(o.toString());
	}

}
