import java.util.ArrayList;
import java.util.List;

/* Arguments:
-e -k ./files/key_short -i ./files/message_short -o ./files/output/enc_short
-d -k ./files/key_short -i ./files/cipher_short -o ./files/output/dec_short
-e -k ./files/key_long -i ./files/message_long -o ./files/output/enc_long
-d -k ./files/key_long -i ./files/cipher_long -o ./files/output/dec_long
*/

public class Main {

	public static void main(String[] args) {
		if(args.length == 7) {
			String instruction = args[0];
			String key_path = args[2];
			String input_path = args[4];
			String output_path = args[6];
			

			switch(instruction) {
			case "-e":
				AES2.enc(input_path, key_path, output_path);
				break;
			case "-d":
				AES2.dec(input_path, key_path, output_path);
				break;
			case "-b":
				String msg = args[2];
				String cipher = args[4];
				AES2.breakAlgorithm(msg, cipher, output_path);
				break;
			default:
				// code block
			}
		}
		else test();
	}


	public static void test() {
		String message_short = "./files/message_short";
		String message_long = "./files/message_long";
		String cipher_short = "./files/cipher_short";
		String cipher_long = "./files/cipher_long";
		String key_short = "./files/key_short";
		String key_long = "./files/key_long";

		List<Test> tests = new ArrayList<>();

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
