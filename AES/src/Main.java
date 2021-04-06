import java.util.ArrayList;
import java.util.List;

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

		List tests = new ArrayList<>();
		
		tests.add(new Test(key_short, message_short, cipher_short));
		tests.add(new Test(key_long, message_long, cipher_long));

		for(int i=0 ; i<tests.size(); i++) {
			boolean ans = ((Test) tests.get(i)).run();
			System.out.println("test no " + (i+1) + " " + ans);
		}
		

		
	}

	public static void print(Object o) {
		System.out.println(o.toString());
	}

}
