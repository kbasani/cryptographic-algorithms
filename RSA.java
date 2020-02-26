//RSA
import java.util.*; 
import java.math.*;

class RSA {
	public static void main(String args[]) { 
		Scanner sc=new Scanner(System.in); 
		int p, q, n, z, d = 0, e, i;

		System.out.println("Enter the number to	be encrypted and decrypted");
		int msg = sc.nextInt();
		 
		double c; BigInteger msgback;

		System.out.println("Enter 1st prime number p:"); 
		p = sc.nextInt();
		if(!isPrime(p))
			return;
		System.out.println("Enter 2nd prime number q:");
		q = sc.nextInt();
		if(!isPrime(q))
			return;

		n = p * q;
		z = (p - 1) * (q - 1);
		System.out.println("the value of z = " + z);

		for(e = 2; e < z ; e++){
		if(gcd(e, z) == 1)	// e is for public key exponent
			break;
		}
		System.out.println("the value of e = " + e);

		for(i = 0; i <= 9; i++){
			int x = 1 + (i * z);
			if(x % e == 0){	//d is for private key exponent 
				d = x / e;
				break;
			}
		}

		System.out.println("the value of d = " + d); 
		c = (Math.pow(msg, e)) % n; 
		System.out.println("Encrypted message is: "); 
		System.out.println(c);

		//converting int value of n to BigInteger 
		BigInteger N = BigInteger.valueOf(n);

		//converting float value of c to BigInteger 
		BigInteger C = BigDecimal.valueOf(c).toBigInteger(); 
		msgback = (C.pow(d)).mod(N); 
		System.out.println("Derypted message is: "); 
		System.out.println(msgback);
	}

	static boolean isPrime(int num){
		for(int i = 2; i < num / 2; i++)
			if(num % i == 0){
				System.out.println("Not a prime number");
				return false;
			}
		return true;

	}
	static int gcd(int a, int b){ 
		if(a==0)
			return b;
		else
			return gcd(b % a, a);

	}
}
