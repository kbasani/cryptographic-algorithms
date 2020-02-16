//Homophonic Substitution Cipher
//For each alphabet in plain text, one character, a pair of characters, or triplets of characters can
//be replaced to generate cipher text.

import java.util.Scanner;
public class HomophonicSubstitution{
	public static String encrypt(String plain, int n, int key){
		int i, j;
		String cipher = "";
		for(i = 0; i < plain.length(); i++){
			char c = plain.charAt(i);
			if(Character.isUpperCase(c)){
				for(j = 0; j < n; j++){
					int x = (c + key - 65 + j) % 26 + 65;
					cipher = cipher + Character.toString((char)x);
				}
			}
			else{
				for(j = 0; j < n; j++){
					int x = (c + key - 97 + j) % 26 + 97;
					cipher = cipher + Character.toString((char)x);
				}
			}
		}
		return cipher;
	}
	public static String decrypt(String cipher, int n, int key){
		int i, j;
		String plain = "";
		for(i = 0; i < cipher.length(); i = i + n){
			char c = cipher.charAt(i);
			if(Character.isUpperCase(c)){
					int x = (c - key - 65 + 26) % 26 + 65;
					plain = plain + Character.toString((char)x);
			}
			else{
					int x = (c - key - 97 + 26) % 26 + 97;
					plain = plain + Character.toString((char)x);
			}
		}
		return plain;
	}
	public static void main (String args[]){
		Scanner sc = new Scanner(System.in);
		String result;
		System.out.println("Enter string:");
		String str = sc.next();
		System.out.println("Enter shift value:");
		int key = sc.nextInt();
		System.out.println("Enter number of characters to substitute \neach character of plain text with:");
		int n = sc.nextInt();

		System.out.println("\nEncryption\nCipher text is:");
		result = encrypt(str, n, key);
		System.out.println(result);

		System.out.println("\nDecryption\nPlain text is: ");
		System.out.println(decrypt(result, n, key));
	}
}