//Hill Cipher
import java.util.Scanner;
import java.lang.Math;

public class HillCipher{
	public static int findDet(int[][] matrix, int size){
		int det = 0, i = 0;
		if(size == 2){
			det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
		}
		else{
			for(int j = 0; j < size; j++){
				det += (int)(Math.pow(-1, i + j)) * (matrix[i][j] * findCofactor(i, j, size, matrix));
			}
		}
		return det;
	}
	public static int findCofactor(int excludingRow, int excludingCol, int size, int[][] matrix){
		int i = excludingRow, j = excludingCol, n = size;
		if(size == 2){
			return matrix[(i + 1) % n][(j + 1) % n];
		}
		return (int)(Math.pow(-1, i + j)) * (matrix[(i + 1) % n][(j + 1) % n] * matrix[(i + 2) % n][(j + 2) % n] - matrix[(i + 1) % n][(j + 2) % n] * matrix[(i + 2) % n][(j + 1) % n]);
	}
	public static int findGCD(int a, int b){
		if(a == 0)
			return b;
		else
			return findGCD(b % a, a);
	}
	public static String printMatrix(int nRows, int nCols, int[][] matrix){
		for(int i = 0; i < nRows; i++){
			for(int j = 0; j < nCols; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		return "\n";
	}
	public static int[][] multiplyMatrices(int[][] firstMatrix, int[][] secondMatrix, int r1, int c1, int c2){
		int[][] product = new int[r1][c2];
		for(int i = 0; i < r1; i++){
			for(int j = 0; j < c2; j++){
				for(int k = 0; k < c1; k++){
					product[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
				}
				product[i][j] = product[i][j] % 26;
			}
		} 
		return product;
	}
	public static void main (String args[]){
		Scanner sc = new Scanner(System.in);
		int i, j, l;
		System.out.println("Enter the key:");
		String keyString = sc.next();
		System.out.println("Enter the size of key: ");
		int keySize = sc.nextInt();
		int key[][] = new int[keySize][keySize];
		for(i = 0; i < keySize; i++){
			for(j = 0; j < keySize; j++){
				key[i][j] = (int)keyString.charAt(i * keySize + j) - (int)'A';
			}
		}
		//print key matrix
		System.out.println("Key matrix is:");
		System.out.println(printMatrix(keySize, keySize, key));
		
		//key validation
		boolean valid = findGCD((findDet(key, keySize)) % 26, 26) == 1 ? true : false;
		if(!valid){
			System.out.println("Key is invalid");
			System.exit(0);
		}
		else{
			System.out.println("Key is valid");
			//Encryption process
			System.out.println("ENCRYPTION\nEnter plain text:");
			String plainString = sc.next();
			int plainLength = plainString.length();
			int plainSize;
			if(plainLength < keySize)
				plainSize = 1;
			else{
				if(plainLength % keySize != 0)
					plainSize = (int)(plainLength / keySize + 1);
				else
					plainSize = (int)(plainLength / keySize);
			}
			int plain[][] = new int[keySize][plainSize];
			for(i = 0; i < keySize; i++){
				for(j = 0; j < plainSize; j++){
					if(i * plainSize + j < plainLength){
						plain[i][j] = (int)plainString.charAt(i * plainSize + j) - (int)'A';
					}
					else{
						plain[i][j] = 0;
					}
				}
			}
			int[][] cipher = multiplyMatrices(key, plain, keySize, keySize, plainSize);
			System.out.println("Cipher matrix is:\n");
			System.out.println(printMatrix(keySize, plainSize, cipher));
			System.out.println("Cipher text is: ");
			for(i = 0; i < keySize; i++)
				for(j = 0; j < plainSize; j++)
					System.out.print((char)(cipher[i][j] + (int)'A'));
			//Decryption
			System.out.println("\nDECRYPTION:");
			//Transpose
			int transpose[][] = new int[keySize][keySize];
			for(i = 0; i < keySize; i++)
				for(j = 0; j < keySize; j++)
					transpose[i][j] = key[j][i];
			//Adjoint
			int adj[][] = new int[keySize][keySize];
			for(i = 0; i < keySize; i++)
				for(j = 0; j < keySize; j++)
					adj[i][j] = (int)Math.pow(-1, i + j) * findCofactor(i, j, keySize, transpose);
			//Inverse
			int dinv = 0;
			for(i = 0; i < 50; i++){
				if((i * findDet(key, keySize)) % 26 == 1){
					dinv = i;
					break; 	
				}
			}
			System.out.println("Value of d inverse is: " + dinv);
			int[][] keyInverse = new int[keySize][keySize];
			for(i = 0; i < keySize; i++){
				for(j = 0; j < keySize; j++){
					adj[i][j] *= dinv;
					adj[i][j] %= 26;
					if(adj[i][j] < 0)
						adj[i][j] += 26;
					keyInverse[i][j] = adj[i][j];
				}
			}
			System.out.println("Inverse of key matrix is:\n");
			System.out.println(printMatrix(keySize, keySize, keyInverse));

			int[][] plainInv = multiplyMatrices(keyInverse, cipher, keySize, keySize, plainSize);
			
			System.out.println("Decrypted matrix is:\n");
			System.out.println(printMatrix(keySize, plainSize, plainInv));
			System.out.println("Decrypted plain text is: ");
			for(i = 0; i < keySize; i++)
				for(j = 0; j < plainSize; j++)
					if(i * plainSize + j < plainLength)
						System.out.print((char)(plainInv[i][j] + (int)'A'));
		}
	}
}