//ROT 13
import java.util.Scanner;
public class ROT13{
	public static void main(String arrgs[]){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter plain text:");
		String plain = sc.next();
		
		System.out.println("\nEncryption\nCipher text is:");
		char ch;
		char[] cipherArray = new char[plain.length()];
		for(int i = 0; i < plain.length(); i++){
			if(Character.isUpperCase(plain.charAt(i)))
				ch = (char)(((int)plain.charAt(i) + 13 - 65) % 26 + 65);
			else
				ch = (char)(((int)plain.charAt(i) + 13 - 97) % 26 + 97);
			cipherArray[i] = ch;
		}
		System.out.println(cipherArray);

		System.out.println("\nDecryption\nPlain text is:");
		String cipher = new String(cipherArray);
		char[] plainArray = new char[cipher.length()];
		for(int i = 0; i < cipher.length(); i++){
			if(Character.isUpperCase(cipher.charAt(i)))
				ch = (char)(((int)cipher.charAt(i) - 13 - 65) % 26 + 65);
			else
				ch = (char)(((int)cipher.charAt(i) - 13 - 97) % 26 + 97);
			plainArray[i] = ch;
		}
		System.out.println(plainArray);
	}	
}