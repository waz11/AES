
public class Main {
	public static void main(String[] args) {

		// read M
		BlocksList msg = new BlocksList("./files/message_long");
		print("M:");
		msg.print();

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

	public static void printArr(char[] arr) {
		for(Object o:arr)
			System.out.print(o+" ");
	}

	public static void print2DArr(String[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j]);
				if(j<4) System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}


}
