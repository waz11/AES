
public class Main {
	public static void main(String[] args) {

		// read M
		String[][] msg = Blocks.fileToHexBlock("./files/message_short");
		print("M:");
		print2DArr(msg);

		// swipe indexes
		print("M - Swipe Indexes:");
		Blocks.swipeIndexex(msg);
		print2DArr(msg);

		// read key:
		String[][] key = Blocks.fileToHexBlock("./files/key_short");
		print("Key:");
		print2DArr(key);

		String[][] roundKey = Blocks.addRoundKey(msg, key);
		print("Round Key:");
		print2DArr(roundKey);
		


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
