import java.io.FileOutputStream;

public class Main {
	public static void main(String[] args) {

		// read M
		BlocksList msg = new BlocksList("./files/message_short");
		print("M:");
		msg.print();
		
		byte[] b = msg.getBlock(0).blockToBytes();
//		b.toString();
		Basics.printByteArray(b);
		
		
        //save byte array to file
		writeByteToFile("./files/results/tring1", b);
		
		

//		// swipe indexes
//		msg.swipeIndexes();
//		print("M - Swipe Indexes:");
//		msg.print();
//
//		// read key:
//		BlocksList key = new BlocksList("./files/key_short");
//		print("Key:");
//		key.print();
//
//		BlocksList roundKey = AES.addRoundKey(msg, key);
//		print("Round Key:");
//		roundKey.print();



	}


	public static void writeByteToFile(String path, byte[] data) {
        //save byte array to file
        try (FileOutputStream fos = new FileOutputStream("./files/ronald/a")) {
            fos.write(data);
        } catch (Exception e) {
            System.out.println("exception write to file");
        }
	}



	//	********* Additional Functions ********* //
	public static void print(Object o) {
		System.out.println(o.toString());
	}

}
