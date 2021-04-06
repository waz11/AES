
public class Main {

	public static void main(String[] args) {
		String message_short = "./files/message_short";
		String message_long = "./files/message_long";
		String cipher_short = "./files/cipher_short";
		String cipher_long = "./files/cipher_long";
		String key_short = "./files/key_short";
		String key_long = "./files/key_long";

		String enc_result_path = "./files/results/result_enc";
		String dec_result_path = "./files/results/result_dec";


//		Test.test_Enc(key_short, message_short, cipher_short, enc_result_path);
//		Test.test_Dec(key_short, enc_result_path, message_short, dec_result_path);
//		Test.test_Dec(key_short, cipher_short, message_short, dec_result_path);
		Test.test_Dec(key_short, cipher_long, message_long, dec_result_path);
	}

	public static void print(Object o) {
		System.out.println(o.toString());
	}

}
