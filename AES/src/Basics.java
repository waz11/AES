
public class Basics {
	//	**CONVERTINGS:
	public static String byteToHex(byte b) {
		String hexString = Integer.toHexString(b & 0xff);
		if(hexString.length()==1)
			hexString = "0";
		return hexString;
	}

	public static byte hexToByte(String hexString) {
		int firstDigit = toDigit(hexString.charAt(0));
		int secondDigit = toDigit(hexString.charAt(1));
		return (byte) ((firstDigit << 4) + secondDigit);
	}

	//	**OPERTAIONS:
	public static String xorHexes(String hex1, String hex2) {
		int num1 = Integer.parseInt(hex1, 16);
		int num2 = Integer.parseInt(hex2, 16);
		byte xor = (byte)(num1 ^ num2);
		return Basics.byteToHex(xor);
	}

	//	**PRINTS:
	public static void printByte(byte b) {
		byte b1 = (byte) b;
		String s1 = String.format("%8s", Integer.toBinaryString(b1 & 0xFF)).replace(' ', '0');
		System.out.println(b+" <-> "+s1); // 10000001
	}



	//	********* Additional Functions ********* //
	private static int toDigit(char hexChar) {
		int digit = Character.digit(hexChar, 16);
		if(digit == -1) {
			throw new IllegalArgumentException(
					"Invalid Hexadecimal Character: "+ hexChar);
		}
		return digit;
	}

}
