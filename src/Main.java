import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import rest.RESTMessagesClientImpl;

public class Main {

	public static void main(String[] args) throws Exception {
		
		
		
		
		String crypted = new StandardPasswordEncoder().encode("root");
		say(crypted);

	}

	private static <T> void  say(T smth){System.out.println(smth);} 
}
