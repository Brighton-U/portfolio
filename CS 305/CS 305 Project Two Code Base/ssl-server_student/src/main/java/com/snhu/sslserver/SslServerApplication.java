package com.snhu.sslserver;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}

@RestController
class ServerController {
	@RequestMapping("/hash")
	public String myHash() {
		String data = "Brighton Ulery";
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));
			String hash = bytesToHex(hashBytes);
			
			return "<p>Data: " + data + "</p><p>Algorithm: SHA-256</p><p>checksum: " + hash + "</p>";
		} catch(NoSuchAlgorithmException e) {
			return "Error generating hash: " + e.getMessage();
		}
	}
	
	private String bytesToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder();
		
		for(byte b : bytes) {
			String hex = Integer.toHexString(0xff & b);
			
			if(hex.length() == 1) {
				hexString.append('0');
			}
			
			hexString.append(hex);
		}
		
		return hexString.toString();
	}
}
