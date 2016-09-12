package web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jaxws.ApplicationService;
import jms.JMSMessages;
import objects.Message;
import objects.MessageForm;
import rest.RESTMessagesClientImpl;
import rest.RESTMessagesClient;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;

@Controller
@RequestMapping({"/"})
public class TestController {

	//for JAX-WS import jaxws.ApplicationService;
	//for RMI import rmiClient.ApplicationService;
	@Autowired
	ApplicationService aplicationService;
	
	@Autowired
	RESTMessagesClient RESTMessagesClient;
	
	@Autowired
	JMSMessages jmsMessages;
	
	@RequestMapping(method=GET)
	public String home() throws Exception{
		
		/*this.aplicationService.saveMessage(new Message());
		
		RESTMessagesClientImpl rest = new RESTMessagesClientImpl();
		Message message = rest.fetchMessage((long) 1);
		System.err.println(message.getTime().toString());*/
		
		return "home";
	}
	
	@RequestMapping(value="/updateMessage/{id}",method=GET)
	public String updateMessage(@PathVariable("id") Long id, Model model) throws Exception{
		Message message = this.RESTMessagesClient.fetchMessage(id);
		message.setMessage("updated");
		Message updated = this.RESTMessagesClient.updateMessage(message);
		model.addAttribute(
				"success", "messge before: "+message.getMessage()+" message after: "+updated.getMessage());
		return "success";
		
	}
	
	@RequestMapping(value="/getMessage/{id}",method=GET)
	public String  getMessage(@PathVariable("id") Long id, Model model) throws Exception{
		Message message = this.RESTMessagesClient.fetchMessage(id);
		model.addAttribute("success", message.getMessage()+" by "+message.getUsername());
		return  "success";
	}
	
	@RequestMapping(value="/scrf-token",method=GET)
	public String  getToken(Model model) throws Exception{
		model.addAttribute("success", this.RESTMessagesClient.getcsrfToken());
		return "success";
	}
	
	@RequestMapping(value="/postMessage",method=GET)
	public String  postMessage(Model model) throws Exception{
		MessageForm postedMessage = new MessageForm();
		postedMessage.setMessage("message here");
		postedMessage.setUsername("vaya");
		Message message = this.RESTMessagesClient.postMessage(postedMessage);
		model.addAttribute("success", "message id "+message.getId());
		return "success";
	}
	
	@RequestMapping(value="/deleteMessage/{id}",method=GET)
	public String  deleteMessage(@PathVariable String id,Model model) throws Exception{
		Message message = this.RESTMessagesClient.deleteMessage(id);
		model.addAttribute("success", "deleted message "+message.getMessage());
		return "success";
	}
	
	@RequestMapping(value="/login",method=GET)
	public String  login() throws Exception{
		this.RESTMessagesClient.login();
		return "home";
	}
	
	//jms testing
	
	
	@RequestMapping(value="/receiveMessage",method=GET)
	public String  receiveMessage(Model model) throws Exception{
		Message message = this.fromJson(this.jmsMessages.receiveMessageObject());
		model.addAttribute("success", "received message "+message.getMessage());
		return "success"; 
	}
	
	@RequestMapping(value="/wanted/{id}",method=GET)
	public String  sendWantedId(@PathVariable String id) throws Exception{
		this.jmsMessages.sendMessage(id);
		return "home";
	}
	
	
	
	
	private Message fromJson(String json){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Message obj = mapper.readValue(json, Message.class);
			return obj;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
