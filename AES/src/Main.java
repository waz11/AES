import java.io.FileOutputStream;

public class Main {
	public static void main(String[] args) {

		// read M
		BlocksList msg = new BlocksList("./files/message_long");
		print("M:");
		msg.print();

		//save block to file of Bytes
		msg.writeToByteFile("./files/results/tring1");

		// swipe indexes
		msg.swipeIndexes();
		print("M - Swipe Indexes:");
		msg.print();

		// read key:
		BlocksList key = new BlocksList("./files/key_short");
		print("Key:");
		key.print();

		BlocksList roundKey = AES.addRoundKey(msg, key);
		print("Round Key:");
		roundKey.print();



	}


	//	********* Additional Functions ********* //
	public static void print(Object o) {
		System.out.println(o.toString());
	}

}
