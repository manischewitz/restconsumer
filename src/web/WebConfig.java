package web;
//examples
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.remoting.JmsInvokerProxyFactoryBean;
import org.springframework.jms.remoting.JmsInvokerServiceExporter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import jms.JMSMessagesImpl;


@Configuration
@EnableWebMvc
@ComponentScan({"rmiClient","web","jaxws","rest","jms"})
public class WebConfig extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/views/");
		irvr.setSuffix(".jsp");
		irvr.setExposeContextBeansAsAttributes(true);
		return irvr;
	}
	
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource rbms = new ReloadableResourceBundleMessageSource();
		
		rbms.setBasename("classpath:messages");
		rbms.setCacheSeconds(10);
		return rbms;
	}

	
	
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
	}
	
	@Bean 
	public ActiveMQConnectionFactory connectionFactory(){
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL("tcp://localhost:61616");
		return factory;
	}
	
	
	@Bean 
	public ActiveMQQueue queue(){
		ActiveMQQueue queue = new ActiveMQQueue("AppQueue");
		return queue;
	}
	
	@Autowired
	MessageListener handler;
	
	@Bean
	public DefaultMessageListenerContainer jmsContainer(){
		DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
		dmlc.setConnectionFactory(this.connectionFactory());
		dmlc.setDestinationName(JMSMessagesImpl.QUEUE_NAME);
		dmlc.setMessageListener(handler);
		return dmlc;
	} 
	
	
	
	
	
	
	
	
	//examples
	
	
	
	
	
}
