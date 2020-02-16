//For each character in plain text, different units of characters 
//can be replaced so that cipher text can be generated.
//Ex: For the plain text abcdefghijklmnopqrstuvwxyz, cipher text can be
// 						 dfi.......................
//(a is shifted 3 characters at a time, b with 4 characters at a time, 
//c is shifted with 6 characters at a time.....)

import java.util.Scanner;
import java.util.Random;
public class ShiftRandomKey{
	public static String encrypt(String plain, int[] key){
		int i, j = 0;
		String cipher = "";
		for(i = 0; i < plain.length(); i++){
			char c = plain.charAt(i);
			int shiftValue = key[j];
			cipher = cipher + Character.toString((char)(((int)c + shiftValue - 97) % 26 + 97));
			j = (j + 1) % key.length;	
		}
		return cipher;
	}

	public static String decrypt(String cipher, int[] key){
		int i, j = 0;
		String plain = "";
		for(i = 0; i < cipher.length(); i++){
			char c = cipher.charAt(i);
			int shiftValue = key[j];
			plain = plain + Character.toString((char)(((int)c -shiftValue - 97) % 26 + 97));
			j = (j + 1) % key.length;
		}
		return plain;
	}

	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int[] key = new int[5];
		int choice;
		String text;
		Random random = new Random();
		//generating key array of random number of shifts
		for(int i = 0; i < key.length; i++){
			key[i] = random.nextInt(10) + 1;
		}
		while(true){
			System.out.println("1.Encrypt 2.Decrypt 3.Exit");
			System.out.println("Enter your choice:");
			choice = sc.nextInt();
			if(choice != 1 && choice != 2)
				break;
			System.out.println("Enter text:");
			text = sc.next();
			switch(choice){
				case 1:
				System.out.println("Encryption\nCipher text: " + encrypt(text, key));
				break;

				case 2:
				System.out.println("Decryption\nPlain text:" + decrypt(text, key));
				break;
			}
		}
	}
}