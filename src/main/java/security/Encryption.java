package security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

	
	public static String makeSecure(String word) {
		try {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] messageDigest = md.digest(word.getBytes());
		BigInteger bi = new BigInteger(1, messageDigest);
		String hashTextInHex = bi.toString(16);
		
		while(hashTextInHex.length() < 32) {
			hashTextInHex = "0" + hashTextInHex;
		}
		
		return hashTextInHex;
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
