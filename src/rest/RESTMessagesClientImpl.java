package rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import objects.Message;
import objects.MessageForm;
@Component
public class RESTMessagesClientImpl implements RESTMessagesClient{

	public Message updateMessage(Message message){
		RestTemplate rest = new RestTemplate();
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(message);
		ResponseEntity<Message> response = rest.exchange(
				"http://localhost:8080/EEProject/messages-api/{id}",
				HttpMethod.PUT,
				requestEntity,
				Message.class,
				message.getId());
		Message messageBeforeUpdate = response.getBody();		 
		return messageBeforeUpdate;
	}
	
	public Message fetchMessage(long id) throws Exception{
		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/json");
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
		ResponseEntity<Message> response = rest.exchange(
				"http://localhost:8080/EEProject/messages-api/{id}",
				HttpMethod.GET,
				requestEntity,
				Message.class,
				id);
		if(response.getStatusCode() == HttpStatus.NOT_FOUND) throw new Exception();
		return response.getBody();
	}
	
	public Message deleteMessage(String id){
		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/json");
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
		ResponseEntity<Message> response = rest.exchange(
				"http://localhost:8080/EEProject/messages-api/{id}",
				HttpMethod.DELETE,
				requestEntity,
				Message.class,
				id);
		Message deletedMessage = response.getBody();		 
		return deletedMessage;
	}
	
	public String getcsrfToken(){
		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/json");
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
		ResponseEntity<String> response = rest.exchange(
				"http://localhost:8080/EEProject/messages-api/csrf-token",
				HttpMethod.GET,
				requestEntity,
				String.class);
		
		return response.getBody();
	}
	
	public Message postMessage(MessageForm postedMessage){
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		//headers.add("X-XSRF-TOKEN", this.getcsrfToken());
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		ResponseEntity<MessageForm> responseEntity = 
				new  ResponseEntity<MessageForm>(postedMessage,headers,HttpStatus.CREATED);
	    
		ResponseEntity<Message> response = rest.exchange(
				"http://localhost:8080/EEProject/messages-api/",
				HttpMethod.POST,
				responseEntity,
				Message.class);
		return response.getBody();		 
	}
	
	public void login(){
		 RestTemplate restTemplate = new RestTemplate();
	     String url = "http://localhost:8080/EEProject/login";
	     HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Basic root:root"); 
	     //headers.add("X-XSRF-TOKEN", this.getcsrfToHttpHeaders headers = new HttpHeaders();ken());
	     //here is some login and pass like this login:pass
	     HttpEntity<Object> request = new HttpEntity<Object>(headers);
	     restTemplate.postForLocation(url, request);
	    
	}
	
	
	
	
	
	
	
	
}
