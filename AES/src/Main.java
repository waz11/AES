
public class Main {
	public static void main(String[] args) {
		String[][] block = Blocks.fileToHexBlock("./files/message_short");
		print2DArr(block);
		System.out.println();
		Blocks.swipeIndexex(block);
		print2DArr(block);

		

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
	}


}
