//Simple Substitution
import java.util.Scanner;
public class SimpleSubstitution{

	public static String encrypt(String plain, String[] key){
		int i, j;
		String cipher = "";
		for(i = 0; i < plain.length(); i++){
			char c = plain.charAt(i);
			int asciiValue = c;
			int pos = asciiValue - 97;
			String x = key[pos];
			cipher = cipher + x;
		}
		return cipher;
	}
	public static String decrypt(String cipher, String[] key){
		int i, j;
		String plain ="";
		for(i = 0; i < cipher.length(); i++){
			String s = Character.toString(cipher.charAt(i));
			for(j = 0; j < key.length; j++){
				if(key[j].equals(s)){
					String x = Character.toString((char)(j + 97));
					plain = plain + x;
				}
			}
		}
		return plain;
	}
	public static void main(String args[]){
		String key[] = {"z", "e", "b", "r", "a", "c", "d",
"f", "g", "h", "i", "j", "k", "l", "m", "o", "p", "q", "s", "t", "u", "v",
"w", "x", "y"}; 
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("\n1. Encrypt\n2. Decrypt \n3. Exit\nChoose your option:");
			int option = sc.nextInt();
			if (option != 1 && option != 2)
				System.exit(0);
			System.out.println("Enter text:");
			String text = sc.next();
			switch(option){
				case 1:
				System.out.println("\nEncryption\nCipher text is:");
				//String result = encrypt(text, key);
				System.out.println(encrypt(text, key));
				break;
				case 2:
				System.out.println("\nDecryption\nPlain text is:");
				System.out.println(decrypt(text, key));
				break;

			}
		}
	}
}