package AES;
import java.util.ArrayList;
import java.util.List;


/* Arguments:
-e -k ./files/key_short -i ./files/message_short -o ./files/output/enc_short
-d -k ./files/key_short -i ./files/cipher_short -o ./files/output/dec_short
-e -k ./files/key_long -i ./files/message_long -o ./files/output/enc_long
-d -k ./files/key_long -i ./files/cipher_long -o ./files/output/dec_long
-b -m ./files/message_short -c ./files/cipher_short -o ./files/output/break/fake_keys
 */

public class Main {

	public static void main(String[] args) {
		if(args.length == 7) {
			String instruction = args[0];
			String key_path = args[2];
			String input_path = args[4];
			String output_path = args[6];

			switch(instruction.toLowerCase()) {
			case "-e":
				AES2.enc(input_path, key_path, output_path);
				break;
			case "-d":
				AES2.dec(input_path, key_path, output_path);
				break;
			case "-b":
				String msg = args[2];
				String cipher = args[4];
				Attack.breakAES2(msg, cipher, output_path);
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

		System.out.println("Testing - 2 tests of Enc & Dec: ");
		for(int i=0 ; i<tests.size(); i++) {
			boolean ans = tests.get(i).run();
			System.out.println("Test no " + (i+1) + " " + ans+"\n");
		}

		// test breaking:
		String breaking = "./files/output/break/fake_keys";
		Attack.breakAES2(message_short, message_short, breaking);
		boolean res = Attack.test();
		System.out.println("break result: "+ res);
	}
}
