package jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component
public class JMSMessageAlertHandler implements MessageListener{

	@Override
	public void onMessage(Message jmsMessage) {
		if(jmsMessage instanceof TextMessage){
			try{
				System.err.println(((TextMessage) jmsMessage).getText()+" RECEIVED");
			}catch(JMSException ex){
				throw new RuntimeException();
			} 
		} else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
		
	}

}
