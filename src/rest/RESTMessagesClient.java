package rest;

import org.springframework.security.web.csrf.CsrfToken;

import objects.Message;
import objects.MessageForm;

public interface RESTMessagesClient {

	public Message updateMessage(Message message);
	public Message fetchMessage(long id) throws Exception;
	public Message deleteMessage(String id);
	public String getcsrfToken();
	public Message postMessage(MessageForm postedMessage);
	public void login();
}
